package com.athen.system.api.cglib.T;

import com.athen.core.util.A;
import com.athen.system.api.cglib.T.model.Result;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * User: chenying
 * Date: 2019-07-16
 * Time: 18:31
 * since: 1.0.0
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    private Class clazz;

    public CglibMethodInterceptor(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        int state  = method.getModifiers();
        System.out.println("执行方法名: "+method.getName()+" 执行方法参数: "+method.getParameterTypes()+" 方法类型: "+state+" 方法参数个数: "+method.getParameterCount());
        Object ret = null;
        if(state==1024){
            if("getResult".equals(method.getName())){
                if(clazz!=null){
                    if(clazz.isEnum()){
                        Enum[] enums = (Enum[]) clazz.getEnumConstants();
                        System.out.println(Arrays.toString(enums));
                    }
                   // String name = obj.
                    if(A.isNotEmpty(objects)){
                        Result result = (Result) objects[0];
                        return result.getRet();
                    }
                }
            }
        }else{
            ret = methodProxy.invokeSuper(o,objects);
        }
        System.out.println("执行完后结果: ret ");
        return ret;
    }
}
