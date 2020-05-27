package com.alibaba.dubbo.rpc.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.athen.core.util.A;
import com.athen.core.util.ContextHolder;
import com.athen.core.util.U;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenying
 * @date 2019-07-13 23:17
 * @time 23:17
 * @since 1.0.0
 **/
@Slf4j
@Activate(group = "provider")
public class DataSourceParamProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String dataSourceKey=RpcContext.getContext().getAttachment(U.DATA_SOURCE_KEY);
        ContextHolder.push(dataSourceKey);
         if(log.isDebugEnabled()){
            log.debug("当前dubbo调用方法：{},dataSourceKey:{}",invocation.getMethodName(),dataSourceKey);
        }
        return invoker.invoke(invocation);
    }
    /**
     * 解析方法参数
     * **/
    private  String  resolveMethodParams(Invocation invocation){
       Class<?>[] clazz = invocation.getParameterTypes();
       if(A.isNotEmpty(clazz)){
           for(Class cl:clazz){
               if(cl.isAssignableFrom(String.class)){
                   //TODO
               }
           }
       }
       return null;
    }
}
