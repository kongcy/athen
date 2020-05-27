package com.athen.system.api.cglib.T;

import com.athen.core.util.U;
import com.athen.system.api.cglib.T.model.Result;
import com.athen.system.api.cglib.T.model.State;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-07-16
 * Time: 18:27
 * since: 1.0.0
 */
public class CglibProxy {
    public static SimpleDateFormat sdf = new SimpleDateFormat(("yyyy-MM"));
    private Map<String, Object> cache = new HashMap<>();

    public static Object getCglibProxy(Class<?> clazz, Callback callback) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(callback);
        return enhancer.create();
    }

    public static void main(String args[]){
        Result rs = new Result();
        rs.setRet("2222");
        BaseTypeHandler proxy = (BaseTypeHandler) getCglibProxy(BaseTypeHandler.class,new CglibMethodInterceptor(State.class));
      //  Object ret = proxy.getResult(rs);
        Object ret = proxy.getResult(rs,1);
        System.out.println("ret----------->"+ret);
        State state = State.Disable;
        System.out.println(U.getMethod(state,"getValue"));

    }
}
