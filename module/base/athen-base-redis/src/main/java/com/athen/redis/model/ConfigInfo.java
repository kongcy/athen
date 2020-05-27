package com.athen.redis.model;

import java.io.Serializable;

import com.athen.config.util.PropertyUtils;
import lombok.Data;

/*** redis配置 **/
@Data
public class ConfigInfo implements Serializable {

	private String domain; // 域名
	/** redis连接信息 ***/
	private String host;
	private String port;
	private String password;

	/** redis连接池配置 **/
	private int timeout;
	private int maxIdle;
	private int maxTotal;
	private long maxWaitMillis;
	private long minEvictableIdleTimeMillis;
	private int numTestsPerEvictionRun;
	private long timeBetweenEvictionRunsMillis;
	private boolean testOnBorrow;
	private boolean testWhileIdle;
	private boolean blockWhenExhausted;

	/** 判断运行环境是线上还是线下 ***/
	private boolean online;

	public ConfigInfo() {
	}

	public ConfigInfo(boolean online) {
		this.online = online;
	}

	public String getDomain() {
		return online ? PropertyUtils.getStrPropertyValue("domain") : domain;
	}

	public String getHost() {
		return online ? PropertyUtils.getStrPropertyValue("host") : host;
	}

	public String getPort() {
		return online ? PropertyUtils.getStrPropertyValue("port") : port;
	}

	public String getPassword() {
		return online ? PropertyUtils.getStrPropertyValue("password") : password;
	}

}
