package com.athen.redis.config;

import com.athen.core.util.LogUtil;
import com.athen.redis.model.ConfigInfo;
import com.athen.redis.service.IRedisService;
import com.athen.redis.service.impl.RedisServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import redis.clients.jedis.JedisPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by foresee on 2019-03-06.
 * redis 配置类
 */
@Configuration
public class RedisServiceConfiguration {
	
	@Value("${online}")
	private boolean online;

	@Bean
	@ConfigurationProperties(prefix = "config")
	public ConfigInfo getConfig() {
		return new ConfigInfo(online);
	}

	/** redis连接池信息 **/
	@Bean
	@ConditionalOnMissingBean
	public JedisPoolConfig poolConfig(ConfigInfo configInfo) {
		if (configInfo == null)
			configInfo = getConfig();
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(configInfo.getMaxTotal());
		jedisPoolConfig.setMaxWaitMillis(configInfo.getMaxWaitMillis());
		jedisPoolConfig.setMinEvictableIdleTimeMillis(configInfo.getMinEvictableIdleTimeMillis());
		jedisPoolConfig.setNumTestsPerEvictionRun(configInfo.getNumTestsPerEvictionRun());
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(configInfo.getTimeBetweenEvictionRunsMillis());
		jedisPoolConfig.setTestOnBorrow(configInfo.isTestOnBorrow());
		jedisPoolConfig.setTestWhileIdle(configInfo.isTestWhileIdle());
		jedisPoolConfig.setBlockWhenExhausted(configInfo.isBlockWhenExhausted());
		return jedisPoolConfig;
	}


	@Bean
	@ConditionalOnMissingBean
	public RedisConnectionFactory getJedisConnectionFactory() {
		ConfigInfo configInfo = getConfig();
		if (LogUtil.ROOT_LOG.isInfoEnabled())
			LogUtil.ROOT_LOG.info("redis config配置信息: {}", configInfo.toString());
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
		standaloneConfig.setDatabase(0);
		standaloneConfig.setHostName(configInfo.getHost());
		standaloneConfig.setPort(Integer.parseInt(configInfo.getPort()));
		standaloneConfig.setPassword(RedisPassword.none());

		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder configurationBuilder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
		configurationBuilder.poolConfig(poolConfig(configInfo));
		return new JedisConnectionFactory(standaloneConfig,configurationBuilder.build());
	}
    /**redis存放key，value json持久化**/
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
	@ConditionalOnMissingBean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(getJedisConnectionFactory());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        // value使用json序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    
    @Bean
	@ConditionalOnMissingBean
    public IRedisService redisService(){
      return new RedisServiceImpl(redisTemplate());
    }

}
