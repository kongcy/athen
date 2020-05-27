package com.athen.datasource.aop;

import com.athen.core.util.ContextHolder;
import com.athen.datasource.annotation.Ds;
import com.athen.datasource.processor.iterator.ProcessBuilder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
/**
 * 注解方法拦截器
 * User: chenying
 * Date: 2019-07-11
 * Time: 17:44
 * since: 1.0.0
 */
@Slf4j
public class DataSourceAnnotationInterceptor implements MethodInterceptor {

    @Setter
    private ProcessBuilder builder;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
 //           ContextHolder.push(determineDatasource(invocation));
            return invocation.proceed();
        }finally {
            ContextHolder.poll();
        }
    }
    /**
     *如果作为服务端单独部署，session这部分应该在消费端处理，不需要在此处处理
     * **/
    private String determineDatasource(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Ds ds = method.isAnnotationPresent(Ds.class) ? method.getAnnotation(Ds.class) : null;
        if (ds != null) {
            String key = ds.value();
            return builder.determineDatasource(invocation, key);
        }
        return "";
    }
}
