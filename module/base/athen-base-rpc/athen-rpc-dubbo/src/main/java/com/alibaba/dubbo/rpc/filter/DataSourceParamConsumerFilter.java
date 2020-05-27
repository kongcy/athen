package com.alibaba.dubbo.rpc.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.athen.core.util.U;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-06
 * Time: 18:28
 * since: 1.0.0
 */
@Slf4j
@Activate(group = "consumer")
public class DataSourceParamConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.debug("invoker:    "+invoker.toString());
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put(U.DATA_SOURCE_KEY, MDC.get(U.DATA_SOURCE_KEY));
        RpcContext.getContext().setAttachments(paramMap);
        return invoker.invoke(invocation);
    }
}
