package com.athen.config.service.impl;

import com.athen.config.model.ConfigTable;
import com.athen.config.model.ConfigTableExample;
import com.athen.config.repository.ConfigTableMapper;
import com.athen.config.service.ConfigService;
import com.athen.core.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 9:49
 * since: 1.0.0
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigTableMapper mapper;

    @Override
    public List<ConfigTable> selectByExampleExt(String application, String profile, String label) {
        ConfigTableExample example = new ConfigTableExample();
        ConfigTableExample.Criteria  criteria = example.createCriteria();
        if(U.isNotBlank(application)){
            criteria.andApplicationEqualTo(application);
        }
        if(U.isNotBlank(profile)){
            example.setProfile(profile);
        }
        return mapper.selectByExampleExt(example);
    }

    @Override
    public List<Map<String,String>> findTables() {
        return mapper.findTables();
    }

    @Override
    public List<ConfigTable> findByApplication(String application) {
        ConfigTableExample example = new ConfigTableExample();
        ConfigTableExample.Criteria criteria = example.createCriteria();
        if(U.isNotBlank(application)){
            criteria.andApplicationEqualTo(application);
            return mapper.selectByExample(example);
        }
        return mapper.selectByExample(null);
    }

    @Override
    public List<ConfigTable> findByApplicationAndProfile(String application, String profile) {
        List<ConfigTable> configTables;
        if(U.isBlank(profile)){
            return findByApplication(application);
        }else{
            List<Map<String,String>> dataList = findTables();
            String tableName = "";
            if(dataList!=null){
                for(Map<String,String> map:dataList){
                    for(Map.Entry<String,String> entry:map.entrySet()){
                        if(entry.getValue().contains(profile)){
                            tableName = entry.getValue();
                            break;
                        }
                    }
                }
            }
            if(U.isNotBlank(tableName)){
                configTables=selectByExampleExt(application,tableName,null);
            }else{
                configTables = findByApplication(null);
            }
        }
        return configTables;
    }


}
