package com.athen.system.web.controller;

import com.athen.core.Constant;
import com.athen.core.json.JsonResult;
import com.athen.core.util.RequestUtils;
import com.athen.dubbo.annotation.FReference;
import com.athen.system.api.service.HeartBeatDomeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.athen.core.json.JsonResult.success;

/**
 * Created by foresee on 2019-01-27.
 */
@RestController
@RequestMapping(value = "/v1/api/dome")
public class DomeController {

    @FReference(version = Constant.DUBBO_VERSION,group = "athenSystem")
    private HeartBeatDomeService heartBeatDomeService;

    @RequestMapping(value = "check-service-action")
    public JsonResult checkServiceStatus(String args){
        return  success("ok",heartBeatDomeService.invoke());
    }
    @RequestMapping(value = "/get-session-info")
    public JsonResult getSessionInfo(){
        Map<String,String> ret = new HashMap<>();
        ret.put("sessionId", RequestUtils.getSession().getId());
        ret.put("url",RequestUtils.getRequest().getContextPath());
        return success("ok",ret);
    }
}
