package com.athen.datasource.spring.boot.autoconfigure;

import com.athen.datasource.DynamicRoutingDataSource;
import com.athen.datasource.aop.DataSourceAnnotationAdvisor;
import com.athen.datasource.aop.DataSourceAnnotationInterceptor;
import com.athen.datasource.creator.DataSourceCreator;
import com.athen.datasource.processor.DsHeaderProcessor;
import com.athen.datasource.processor.DsProcessor;
import com.athen.datasource.processor.DsSessionProcessor;
import com.athen.datasource.processor.DsSpelExpressionProcessor;
import com.athen.datasource.processor.iterator.ProcessBuilder;
import com.athen.datasource.provider.DataSourceProvider;
import com.athen.datasource.provider.YmlDataSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
/**
 * 数据源自动配置类
 * User: chenying
 * Date: 2019-07-12
 * Time: 15:25
 * since: 1.0.0
 */
@Configuration
@Import(SessionFactoryConfiguration.class)
@EnableConfigurationProperties(DataSourceConfig.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DynamicDataSourceConfiguration {

     @Autowired
     private DataSourceConfig config;

    /**
     * 创建dataSource
     **/
    @Bean
    @ConditionalOnMissingBean
    public DataSource createDataSource(DataSourceProvider provider) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setProvider(provider);
        dataSource.setP6spy(config.getP6spy());
        dataSource.setPrimary(config.getPrimary());
        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceProvider dynamicDataSourceProvider(DataSourceCreator dataSourceCreator) {
        return new YmlDataSourceProvider(dataSourceCreator,config);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceCreator dynamicDataSourceCreator(@Autowired WebApplicationContext webApplicationContext) {
        DataSourceCreator dataSourceCreator = new DataSourceCreator();
        dataSourceCreator.setApplicationContext(webApplicationContext);
        dataSourceCreator.setDruidGlobalConfig(config.getDruid());
        dataSourceCreator.setHikariGlobalConfig(config.getHikari());
        return dataSourceCreator;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProcessBuilder dsProcessor() {
        List<DsProcessor> ds = new ArrayList<>();
        ds.add(new DsSpelExpressionProcessor());
        ds.add(new DsSessionProcessor());
        ds.add(new DsHeaderProcessor());
        return new ProcessBuilder(ds);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor(ProcessBuilder builder) {
        DataSourceAnnotationInterceptor interceptor = new DataSourceAnnotationInterceptor();
        interceptor.setBuilder(builder);
        DataSourceAnnotationAdvisor advisor = new DataSourceAnnotationAdvisor(interceptor);
        advisor.setOrder(config.getOrder());
        return advisor;
    }

}
