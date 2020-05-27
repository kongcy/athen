package com.athen.datasource.spring.boot.autoconfigure;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.athen.core.util.A;
import com.athen.core.util.LogUtil;
import com.athen.datasource.model.HandlerEntity;
import com.athen.datasource.toolkit.ResourceUtils;
import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * mybaties session 配置
 * @author chenying
 * @date 2019-07-13 14:41
 * @time 14:41
 * @since 1.0.0
 **/
@Configuration
@EnableConfigurationProperties(HandlerEntity.class)
public class SessionFactoryConfiguration {


    @Autowired
    private HandlerEntity handler;

    private OffsetLimitInterceptor mybatisPage() {
        OffsetLimitInterceptor pageInterceptor = new OffsetLimitInterceptor();
        pageInterceptor.setDialectClass(MySQLDialect.class.getName());
        return pageInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //加载mybaties的sql xml文件
        Resource[] resources = ResourceUtils.getResourceArray(ResourceUtils.RESOURCE_PATH);
        if (LogUtil.ROOT_LOG.isDebugEnabled()) {
            LogUtil.ROOT_LOG.debug("mybatis load xml:({})", A.toStr(resources));
        }
        sessionFactory.setMapperLocations(resources);
        //处理mybaties特殊枚举对象转换
        sessionFactory.setTypeHandlers(ResourceUtils.getHandleArray(handler.getPackagesName(),this.getClass().getClassLoader()));
        sessionFactory.setPlugins(new Interceptor[]{mybatisPage()});
        return sessionFactory.getObject();
    }

    @Bean(name = "sqlSessionTemplate", destroyMethod = "clearCache")
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(DataSource dataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(dataSource));
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean<StatViewServlet> bean=new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "chenying521");
        initParams.put("allow", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        return bean;
    }
}
