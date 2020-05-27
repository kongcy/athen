package com.athen.system.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.athen.core.Constant;
import com.athen.dubbo.annotation.FService;
import com.athen.system.api.service.HeartBeatDomeService;
import org.springframework.stereotype.Component;

/**
 * Created by foresee on 2019-01-27.
 */
@Component
@FService(version = Constant.DUBBO_VERSION,group = "athenSystem",interfaceClass = HeartBeatDomeService.class)
public class HeartBeatDomeServiceImpl implements HeartBeatDomeService {
    @Override
    public String invoke() {
        return "Dubbo System service start !!!!";
    }
}
