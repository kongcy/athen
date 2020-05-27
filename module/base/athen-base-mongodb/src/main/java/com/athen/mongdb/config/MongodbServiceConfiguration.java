package com.athen.mongdb.config;

import com.athen.core.util.U;
import com.athen.mongdb.model.MongoConfig;
import com.athen.mongdb.service.IMongodbBaseDAO;
import com.athen.mongdb.service.impl.MongodbBaseDAOExtImpl;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: chenying
 * Date: 2019-08-09
 * Time: 15:06
 * since: 1.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MongoConfig.class)
public class MongodbServiceConfiguration {

    @Autowired
    private MongoConfig mongoConfig;

    @Bean
    @ConditionalOnMissingBean
    public MongoDbFactory mongoDbFactory(){
        if(log.isInfoEnabled()){
            log.info("Mongodb连接池配置 mongoConfig:  {}",mongoConfig==null?"":mongoConfig.toString());
        }
        MongoClientOptions mongoClientOptions = getMongoClientOptions(mongoConfig);
        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (String address : mongoConfig.getAddress()) {
            String[] hostAndPort = address.split(":");
            String host = hostAndPort[0];
            Integer port = Integer.parseInt(hostAndPort[1]);
            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }
        //链接认证
        MongoCredential mongoCredential = getMongoCredential(mongoConfig);
        // 创建认证客户端
        MongoClient mongoClient=null;
        if(mongoCredential!=null){
            mongoClient= new MongoClient(serverAddresses,mongoCredential,mongoClientOptions);
        }else{
            mongoClient = new MongoClient(serverAddresses,mongoClientOptions);
        }
        // 创建MongoDbFactory
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, mongoConfig.getDatabase());
        return mongoDbFactory;
    }
    @Bean
    @ConditionalOnMissingBean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    public IMongodbBaseDAO  mongodbBaseDAO(){
        return new MongodbBaseDAOExtImpl(mongoTemplate());
    }

    /**获取MongoCredential**/
    private MongoCredential  getMongoCredential(MongoConfig mongoConfig){
        if(mongoConfig!=null&& U.isNotBlank(mongoConfig.getUsername())){
            String database = U.isBlank(mongoConfig.getAuthenticationDatabase())?mongoConfig.getDatabase():mongoConfig.getAuthenticationDatabase();
            String password = U.isBlank(mongoConfig.getPassword())?"":mongoConfig.getPassword();
            return MongoCredential.createScramSha1Credential(mongoConfig.getUsername(),database,password.toCharArray());
        }
        return null;
    }
    /**获取MongoClientOptions**/
    private MongoClientOptions getMongoClientOptions(MongoConfig mongoConfig){
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(mongoConfig.getMaxConnectionsPerHost());
        builder.minConnectionsPerHost(mongoConfig.getMinConnectionsPerHost());
        if (mongoConfig.getReplicaSet() != null) {
            builder.requiredReplicaSetName(mongoConfig.getReplicaSet());
        }
        builder.threadsAllowedToBlockForConnectionMultiplier(
                mongoConfig.getThreadsAllowedToBlockForConnectionMultiplier());
        builder.serverSelectionTimeout(mongoConfig.getServerSelectionTimeout());
        builder.maxWaitTime(mongoConfig.getMaxWaitTime());
        builder.maxConnectionIdleTime(mongoConfig.getMaxConnectionIdleTime());
        builder.maxConnectionLifeTime(mongoConfig.getMaxConnectionLifeTime());
        builder.connectTimeout(mongoConfig.getConnectTimeout());
        builder.socketTimeout(mongoConfig.getSocketTimeout());
        // builder.socketKeepAlive(mongoConfig.getSocketKeepAlive());
        builder.sslEnabled(mongoConfig.getSslEnabled());
        builder.sslInvalidHostNameAllowed(mongoConfig.getSslInvalidHostNameAllowed());
        builder.alwaysUseMBeans(mongoConfig.getAlwaysUseMBeans());
        builder.heartbeatFrequency(mongoConfig.getHeartbeatFrequency());
        builder.minHeartbeatFrequency(mongoConfig.getMinHeartbeatFrequency());
        builder.heartbeatConnectTimeout(mongoConfig.getHeartbeatConnectTimeout());
        builder.heartbeatSocketTimeout(mongoConfig.getHeartbeatSocketTimeout());
        builder.localThreshold(mongoConfig.getLocalThreshold());
        return builder.build();
    }

}
