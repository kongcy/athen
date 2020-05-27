package com.athen.config.model;

import com.athen.core.util.A;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 15:12
 * since: 1.0.0
 */
@Data
public class Config implements Serializable {
    private String name;
    private List<String> profiles;
    private String version;
    private List<PropertySource> propertySources;

    @Data
    public static class PropertySource{
        private String name;
        private Map<String,Object> source;
    }

    public Map<String,Object> getData(){
        PropertySource source = A.first(this.getPropertySources());
        if(source!=null){
            return source.getSource();
        }
        return null;
    }
}
