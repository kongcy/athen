package com.athen.system.api.cglib.T;

import com.athen.system.api.cglib.T.model.Method;
import com.athen.system.api.cglib.T.model.Result;

/**
 * User: chenying
 * Date: 2019-07-16
 * Time: 18:13
 * since: 1.0.0
 */
public interface TypeHandler<T> {

    void setMethod(Method method, String s, T parmas);

    T getResult(Result rs, int s);
}
