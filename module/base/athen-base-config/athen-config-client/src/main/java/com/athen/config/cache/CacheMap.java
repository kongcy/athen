package com.athen.config.cache;

import com.athen.core.util.A;

import java.util.HashMap;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 16:06
 * since: 1.0.0
 */
public abstract class CacheMap {

    private static Map<String,Object> cache = new HashMap<>();

    public static Map<String,Object> getCache(){
        return cache;
    }
    public static void setCache(Map<String,Object> dataMap){
        if(A.isNotEmpty(dataMap)){
            cache.clear();
            cache.putAll(dataMap);
        }
    }
}
