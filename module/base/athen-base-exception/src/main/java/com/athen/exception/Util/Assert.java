package com.athen.exception.Util;

import com.athen.core.util.U;
import com.athen.exception.AccountException;
import com.athen.exception.ParamServiceException;
import com.athen.exception.ServiceException;

/**
 * Created by foresee on 2019-01-27.
 */
public abstract class Assert {

    public static void isBlank(Object obj, String msg) {
        if (U.isBlank(obj))
            throw new ServiceException(msg);
    }

    public static void assertAccountException(Object obj, String msg) {
        if (U.isBlank(obj))
            throw new AccountException(msg);
    }

    public static void assertTrueAccountException(boolean isTrue, String msg) {
        if (isTrue) throw new AccountException(msg);
    }

    public static void assertFalseAccountException(boolean isFalse, String msg) {
        assertTrueAccountException(!isFalse,msg);
    }
}
