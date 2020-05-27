package com.athen.datasource;

import com.athen.core.util.ContextHolder;
import com.athen.datasource.provider.DataSourceProvider;
import com.p6spy.engine.spy.P6DataSource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**子类来决定实现数据源
 * User: chenying
 * Date: 2019-07-12
 * Time: 18:04
 * since: 1.0.0
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {

    @Setter
    private DataSourceProvider provider;
   // @Setter
   // private Class<? extends DynamicDataSourceStrategy> strategy;
    @Setter
    private String primary;
    @Setter
    private boolean strict;
    private boolean p6spy;

    /**
     * 所有数据库
     */
    private Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();


    @Override
    public DataSource determineDataSource() {
        return getDataSource(ContextHolder.peek());
    }

    private DataSource determinePrimaryDataSource() {
        log.debug("从默认{}数据源中返回数据",primary);
        return  dataSourceMap.get(primary);
    }

    /**
     * 获取当前所有的数据源
     *
     * @return 当前所有数据源
     */
    public Map<String, DataSource> getCurrentDataSources() {
        return dataSourceMap;
    }


    /**
     * 获取数据源
     *
     * @param ds 数据源名称
     * @return 数据源
     */
    public DataSource getDataSource(String ds) {
        DataSource dataSource = dataSourceMap.get(ds);
        if(dataSource==null){
            dataSource = determinePrimaryDataSource();
        }
        return dataSource;
    }

    /**
     * 添加数据源
     *
     * @param ds         数据源名称
     * @param dataSource 数据源
     */
    public synchronized void addDataSource(String ds, DataSource dataSource) {
        if (p6spy) {
            dataSource = new P6DataSource(dataSource);
        }
        dataSourceMap.put(ds, dataSource);
        log.info("动态数据源-加载 {} 成功", ds);
    }

    /**
     * 删除数据源
     *
     * @param ds 数据源名称
     */
    public synchronized void removeDataSource(String ds) {
        if (dataSourceMap.containsKey(ds)) {
            DataSource dataSource = dataSourceMap.get(ds);
            dataSourceMap.remove(ds);
            log.info("动态数据源-删除 {} 成功", ds);
        } else {
            log.warn("动态数据源-未找到 {} 数据源", ds);
        }
    }

    public void setP6spy(boolean p6spy) {
        if (p6spy) {
            try {
                Class.forName("com.p6spy.engine.spy.P6DataSource");
                log.info("动态数据源-检测到并开启了p6spy");
                this.p6spy = true;
            } catch (Exception e) {
                log.warn("多数据源启动器开启了p6spy但并未引入相关依赖");
            }
        } else {
            this.p6spy = false;
        }
    }

    @Override
    public void destroy() throws Exception {
        log.info("closing dynamicDatasource  ing....");
        for (Map.Entry<String, DataSource> item : dataSourceMap.entrySet()) {
            DataSource dataSource = item.getValue();
            if (p6spy) {
                Field realDataSourceField = P6DataSource.class.getDeclaredField("realDataSource");
                realDataSourceField.setAccessible(true);
                dataSource = (DataSource) realDataSourceField.get(dataSource);
            }
            Class<? extends DataSource> clazz = dataSource.getClass();
            try {
                Method closeMethod = clazz.getDeclaredMethod("close");
                closeMethod.invoke(dataSource);
            } catch (NoSuchMethodException e) {
                log.warn("关闭数据源 {} 失败,", item.getKey());
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, DataSource> dataSources = provider.loadDataSource();
        log.info("初始共加载 {} 个数据源", dataSources.size());
        //添加并分组数据源
        for (Map.Entry<String, DataSource> dsItem : dataSources.entrySet()) {
            addDataSource(dsItem.getKey(), dsItem.getValue());
        }
        //检测默认数据源设置
    }

}
