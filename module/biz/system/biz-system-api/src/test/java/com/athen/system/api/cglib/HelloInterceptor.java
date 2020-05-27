package com.athen.system.api.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * User: chenying
 * Date: 2019-07-16
 * Time: 17:59
 * since: 1.0.0
 */
public class HelloInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object obj = methodProxy.invokeSuper(o,objects);
        System.out.println("after");
        return obj;
    }
}
