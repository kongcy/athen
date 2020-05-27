package com.athen.datasource.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**jdk 代理类
 * User: chenying
 * Date: 2019-08-05
 * Time: 16:53
 * since: 1.0.0
 */
public class JdkProxy implements InvocationHandler {

    public  Object createProxy(Class clazz){
      //  return Proxy.newProxyInstance(clazz.getClassLoader(),clazz,this);
        return  null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return null;
    }


}
