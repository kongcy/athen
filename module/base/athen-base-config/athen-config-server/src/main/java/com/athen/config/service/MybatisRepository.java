package com.athen.config.service;

import com.athen.config.model.ConfigTable;
import com.athen.core.json.JsonUtil;
import com.athen.core.util.U;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis实现，
 * User: chenying
 * Date: 2019-08-13
 * Time: 17:34
 * since: 1.0.0
 */
@Slf4j
@Configuration
@Profile("mybatis")
public class MybatisRepository implements EnvironmentRepository, Ordered {
    @Resource
    private ConfigService service;

    @Override
    public Environment findOne(String application, String profile, String label) {
        log.info("MybatisRepository--->application：{}, profile: {}, label: {}",application,profile,label);
        if(U.isBlank(application))
            return null;
        List<ConfigTable> configTables = service.findByApplicationAndProfile(application,profile);
        if(log.isInfoEnabled()){
            log.info("获取配置属性 configTables：{}", JsonUtil.toJson(configTables));
        }
        Map<String,String> map = new HashMap<>();
        for(ConfigTable config:configTables){
            map.put(config.getModule()+"."+config.getKey(),config.getValue());
        }
        Environment environment = new Environment(application);
        environment.add(new PropertySource(application,map));
        return environment;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
