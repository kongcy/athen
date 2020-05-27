package com.athen.system.api.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * User: chenying
 * Date: 2019-07-16
 * Time: 18:02
 * since: 1.0.0
 */
public class Main {
    public static void main(String args[]){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new HelloInterceptor());
        HelloService proxy = (HelloService) enhancer.create();
        proxy.say("111");
        proxy.sayOne("222");
    }
}
