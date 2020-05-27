package com.alibaba.dubbo.rpc.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.ReflectUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.athen.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * User: chenying
 * Date: 2019-08-01
 * Time: 15:44
 * since: 1.0.0
 * 覆盖dubbo的异常过滤器,添加自定义异常
 * 异常过滤器
 * dubbo的异常处理类是com.alibaba.dubbo.rpc.filter.ExceptionFilter 类,源码这里就不贴了.归纳下对异常的处理分为下面几类:
 * 1)如果provider实现了GenericService接口,直接抛出
 * 2)如果是checked异常，直接抛出
 * 3)在方法签名上有声明，直接抛出
 * 4)异常类和接口类在同一jar包里，直接抛出
 * 5)是JDK自带的异常，直接抛出
 * 6)是Dubbo本身的异常，直接抛出
 * 7)否则，包装成RuntimeException抛给客户端
 */
@Slf4j
@Activate(group = "provider")
public class ExceptionFilter implements Filter {

    /**
     * do invoke filter.
     * <p/>
     * <code>
     * // before filter
     * Result result = invoker.invoke(invocation);
     * // after filter
     * return result;
     * </code>
     *
     * @param invoker    service
     * @param invocation invocation.
     * @return invoke result.
     * @throws RpcException
     * @see Invoker#invoke(Invocation)
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        {
            try {
                Result result = invoker.invoke(invocation);
                if (result.hasException() && GenericService.class != invoker.getInterface()) {
                    try {
                        Throwable exception = result.getException();

                        // directly throw if it's checked exception
                        if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                            return result;
                        }
                        // directly throw if the exception appears in the signature
                        try {
                            Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                            Class<?>[] exceptionClassses = method.getExceptionTypes();
                            for (Class<?> exceptionClass : exceptionClassses) {
                                if (exception.getClass().equals(exceptionClass)) {
                                    return result;
                                }
                            }
                        } catch (NoSuchMethodException e) {
                            return result;
                        }

                        // for the exception not found in method's signature, print ERROR message in server's log.
                        log.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost()
                                + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                                + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);

                        // directly throw if exception class and interface class are in the same jar file.
                        String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                        String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                        if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)) {
                            return result;
                        }
                        // directly throw if it's JDK exception
                        String className = exception.getClass().getName();
                        if (className.startsWith("java.") || className.startsWith("javax.")) {
                            return result;
                        }
                        // directly throw if it's dubbo exception
                        if (exception instanceof RpcException) {
                            return result;
                        }
                        //自定义异常!
                        if(exception instanceof ServiceException){
                            return result;
                        }

                        // otherwise, wrap with RuntimeException and throw back to the client
                        return new RpcResult(new RuntimeException(StringUtils.toString(exception)));
                    } catch (Throwable e) {
                        log.warn("Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost()
                                + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                                + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
                        return result;
                    }
                }
                return result;
            } catch (RuntimeException e) {
                log.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost()
                        + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                        + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
                throw e;
            }
        }
    }
}
