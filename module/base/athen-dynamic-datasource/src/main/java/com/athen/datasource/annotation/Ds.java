package com.athen.datasource.annotation;

import java.lang.annotation.*;

/**
 * User: chenying
 * Date: 2019-07-11
 * Time: 17:14
 * since: 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ds {

    String value() default "";
}
