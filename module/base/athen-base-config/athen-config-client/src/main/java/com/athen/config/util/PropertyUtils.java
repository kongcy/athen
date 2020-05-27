package com.athen.config.util;

import com.athen.config.cache.CacheMap;
import com.athen.config.model.Config;
import com.athen.core.date.DateFormatType;
import com.athen.core.date.DateUtil;
import com.athen.core.json.JsonUtil;
import com.athen.core.util.A;
import com.athen.core.util.HttpUtil;
import com.athen.core.util.U;
import com.athen.exception.ConfigCenterNoFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 属性文件工具类
 * User: chenying
 * Date: 2019-08-14
 * Time: 14:18
 * since: 1.0.0
 */
@Slf4j
public abstract class PropertyUtils {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    static {
        init();
    }

    private static void init() {
        CacheMap.setCache(getDataFromConfig());
        refreshData();
    }

    /**
     * 读取配置
     **/
    private static Map<String, Object> getDataFromConfig() {
        String configUrl = getEnvValue("DconfCenterUrl");
        if (U.isBlank(configUrl)) {
            throw new ConfigCenterNoFoundException("启动参数配置中心地址找不到: DconfCenterUrl");
        }
        String user = getEnvValue("DconfUser");
        try {
            if (log.isInfoEnabled()) {
                log.info("获取配置中心地址URL: {}", configUrl);
            }
            Map<String, Object> params = A.maps("user", user);
            String json = HttpUtil.get(configUrl, params);
            if (U.isNotBlank(json)) {
                Config config = JsonUtil.fromJson(json, Config.class);
                if (config != null) {
                    return config.getData();
                }
            }
        } catch (Exception e) {
            log.error("获取配置属性出现异常！" + e.getMessage());
        }
        return null;
    }


    private static String getEnvValue(String key) {
        if (U.isBlank(key)) {
            return null;
        }
        String val = System.getProperty(key);
        if (U.isBlank(val)) {
            val = System.getenv(key);
        }
        return val;
    }

    /**
     * 定时刷新配置文件 延迟10s 每隔2分钟
     **/
    private static void refreshData() {
        executorService.scheduleAtFixedRate(() -> {
            try {
                Map<String, Object> ret = getDataFromConfig();
                if (log.isInfoEnabled()) {
                    log.info("定时刷新配置文件 当前时间： {}, 配置属性count: {} ", DateUtil.now(DateFormatType.CN_YYYY_MM_DD_HH_MM_SS), ret == null ? 0 : ret.size());
                }
                if (ret != null) {
                    CacheMap.setCache(ret);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }, 10L, 120L, TimeUnit.SECONDS);
    }

    public static Object getProperty(String key) {
        if (U.isBlank(key)) {
            return null;
        }
        return CacheMap.getCache().get(key);
    }

    /**
     * 获取字符串属性值
     * **/
    public static String getStrPropertyValue(String key){
        if(U.isBlank(key)){
            return "";
        }
        Object obj = CacheMap.getCache().get(key);
        if(U.isBlank(obj)){
            return "";
        }
        return obj.toString();
    }


    public static void main(String[] args) {
        String loginUrl = "http://localhost:9072/config-server/login";
        Map<String, Object> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password", "chenying521");
        //  System.out.println(HttpUtil.post(loginUrl,params));
        String url = "http://localhost:9072/config-server/configTable/dev";
        String ret = HttpUtil.get(url, params);
        System.out.println(ret);
        Config config = JsonUtil.fromJson(ret, Config.class);
        Map<String, Object> dataMap = config.getData();
        System.out.println(dataMap.get("system.url"));
    }
}
