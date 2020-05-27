package com.athen.system.api.cglib.T;

import com.athen.system.api.cglib.T.model.Method;
import com.athen.system.api.cglib.T.model.Result;

/**
 * User: chenying
 * Date: 2019-07-16
 * Time: 18:21
 * since: 1.0.0
 */
public  abstract  class BaseTypeHandler<T> implements TypeHandler<T> {
    @Override
    public void setMethod(Method method, String s, T parmas) {

    }

    @Override
    public T getResult(Result rs, int s) {
        return getResult(rs);
    }

    abstract T getResult(Result rs);
}
