package com.athen.system.api.cglib;

/**
 * cglib 动态代理dome
 * User: chenying
 * Date: 2019-07-16
 * Time: 17:54
 * since: 1.0.0
 */
public class HelloService {
    public HelloService() {
    }

    final public String say(String s) {
        System.out.println("hello---->我是final say(" + s + ")");
        return null;
    }

    public String sayOne(String s) {
        System.out.println("hello---->我是final sayOne(" + s + ")");
        return null;
    }
}
