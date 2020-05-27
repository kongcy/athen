package com.athen.system.config;

import com.athen.core.util.LogUtil;
import com.athen.core.util.RequestUtils;
import com.athen.system.annotation.NotNeedLogin;
import com.athen.system.annotation.NotNeedPermission;
import com.athen.system.util.SessionUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.List;

public class ManagerInterceptor implements HandlerInterceptor {

    @Value("${online:false}")
    private boolean online;

    private static final List<String> LET_IT_GO = Lists.newArrayList("/error");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        bindParam();
        checkLoginAndPermission(handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        if (ex != null) {
            if (LogUtil.ROOT_LOG.isErrorEnabled())
                LogUtil.ROOT_LOG.error("request was over, but have exception: " + ex.getMessage());
        }
        unbindParam();
    }

    private void bindParam() {
        // 打印日志上下文中的数据
        LogUtil.Mdc.bind(SessionUtil.getUserId(), SessionUtil.getUserName());
    }

    private void unbindParam() {
        // 删除打印日志上下文中的数据
        LogUtil.Mdc.unbind();
    }

    /**
     * 检查登录及权限
     */
    private void checkLoginAndPermission(Object handler) {
        if (LET_IT_GO.contains(RequestUtils.getRequest().getRequestURI())) return;
        // 如果是非线上环境则不验证登录及权限
        // if (!online) return;
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) return;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 在不需要登录的 url 上标注 @NotNeedLogin
        NotNeedLogin notNeedLogin = getAnnotation(handlerMethod, NotNeedLogin.class);
        // 标注了 NotNeedLogin 且 flag 为 true(默认就是 true)则表示当前的请求不需要验证登录
        if (notNeedLogin != null && notNeedLogin.flag()) return;

        // 检查是否有登录
        SessionUtil.checkLogin();

        // 当前用户如果是超级管理员, 则不需要往下继续验证权限
     //   if (SessionUtil.isSuper()) return;

        // 在不需要验证权限的 url 上标注 @NotNeedPermission
        NotNeedPermission notNeedPermission = getAnnotation(handlerMethod, NotNeedPermission.class);
        // 标注了 NotNeedPermission 且 flag 为 true(默认就是 true)则表示当前的请求不需要验证权限
        if (notNeedPermission != null && notNeedPermission.flag()) return;
        // 检查权限
        SessionUtil.checkPermission();
    }

    private <T extends Annotation> T getAnnotation(HandlerMethod handlerMethod, Class<T> clazz) {
        // 先找方法上的注解, 再找类上的注解
        T annotation = handlerMethod.getMethodAnnotation(clazz);
        return annotation == null ? handlerMethod.getBean().getClass().getAnnotation(clazz) : annotation;
    }

}
