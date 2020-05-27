package com.athen.datasource.provider;

import com.athen.datasource.creator.DataSourceCreator;
import com.athen.datasource.spring.boot.autoconfigure.DataSourceConfig;
import com.athen.datasource.spring.boot.autoconfigure.DataSourceProperty;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * yml配置中获取数据源配置
 * User: chenying
 * Date: 2019-07-12
 * Time: 17:30
 * since: 1.0.0
 */
public class YmlDataSourceProvider implements DataSourceProvider {

    private DataSourceConfig config;
    private DataSourceCreator creator;

    public YmlDataSourceProvider(DataSourceCreator creator, DataSourceConfig config) {
        this.creator = creator;
        this.config = config;
    }

    @Override
    public Map<String, DataSource> loadDataSource() {
        Map<String, DataSourceProperty> propertyMap = config.getGroup();
        Map<String, DataSource> ds = new HashMap<>();
        if (propertyMap != null) {
            for (String key : propertyMap.keySet()) {
                DataSourceProperty property = propertyMap.get(key);
                DataSource dataSource = creator.createDataSource(property);
                ds.put(key, dataSource);
            }
        }
        return ds;
    }
}
