package com.athen.system.util;


import com.athen.core.util.A;
import com.athen.core.util.RequestUtils;
import com.athen.core.util.U;
import com.athen.exception.ForbiddenException;
import com.athen.exception.NotLoginException;
import com.athen.system.api.model.Resource;
import com.athen.system.api.model.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.athen.core.util.LogUtil.ROOT_LOG;

/**
 * 操作 session 都基于此, 其他地方不允许操作!
 */
public  class SessionUtil {

    private static final Long DEFAULT_ID = 0l;
    private static final String DEFAULT_NAME = "未登录用户";
    /**
     * 放在 session 里的图片验证码 key
     */
    private static final String CODE = SessionUtil.class.getName() + "-CODE";
    /**
     * 放在 session 里的用户
     */
    public static final String USER = SessionUtil.class.getName() + "-USER";
    /**
     * 用户权限
     */
    public static final String RESOURCE = SessionUtil.class.getName() + "-RESOURCE";

    /**
     * 超级管理员账号
     */
    private static final List<String> SUPER_USER = A.lists("admin", "root");

    /**
     * 验证图片验证码
     */
    public static boolean checkImageCode(String code) {
        if (U.isBlank(code)) return false;

        Object securityCode = RequestUtils.getSession().getAttribute(CODE);
        return securityCode != null && code.equalsIgnoreCase(securityCode.toString());
    }

    /**
     * 将图片验证码的值放入 session
     */
    public static void putImageCode(String code) {
        RequestUtils.getSession().setAttribute(CODE, code);
        if (ROOT_LOG.isDebugEnabled()) {
            ROOT_LOG.debug("put image code ({}) in session ({})", code, RequestUtils.getSession().getId());
        }
    }


    /**
     * 注册登录后, 把 用户信息 写入 session
     */
    public static void signIn(SysUser user, List<Resource> resources) {
        RequestUtils.getSession().setAttribute(USER, user);
        saveRes(resources);
    }

    /**
     * 操作资源的时候直接更新次方法
     */
    public static void saveRes(List<Resource> res) {
        RequestUtils.getSession().setAttribute(RESOURCE, res);
    }

    /**
     * 退出登录.
     */
    public static void signOut() {
        RequestUtils.getSession().invalidate();
    }

    public static SysUser getUser() {
        Object user = RequestUtils.getSession().getAttribute(USER);
        return user == null ? null : (SysUser) user;
    }

    @SuppressWarnings("unchecked")
    public static List<Resource> getResource() {
        Object resources = RequestUtils.getSession().getAttribute(RESOURCE);
        return resources == null ? null : (List<Resource>) resources;
    }


    /**
     * 获取 session 中的 userId
     */
    public static Long getUserId() {
        SysUser user = getUser();
        return (user == null) ? DEFAULT_ID : user.getId();
    }

    /**
     * 获取 session 中的 userName
     */
    public static String getUserName() {
        SysUser user = getUser();
        return (user == null) ? DEFAULT_NAME : user.getUserName();
    }


    /**
     * 是否是超级管理员, 是则返回 true
     */
    public static boolean isSuper() {
        SysUser user = getUser();
        return user != null && SUPER_USER.contains(user.getLoginName());
    }

    /**
     * 是否是超级管理员, 不是则返回 true
     */
    public static boolean isNotSuper() {
        return !isSuper();
    }

    /**
     * 验证用户是否有登录, 有登录则返回 true
     */
    public static boolean isLogin() {
        // 从 session 中获取的 用户id 和 用户名 都不是默认值就表示登录了
        return !DEFAULT_ID.equals(getUserId()) && !DEFAULT_NAME.equals(getUserName());
    }

    /**
     * 验证用户是否有登录, 没有登录则返回 true
     */
    public static boolean isNotLogin() {
        return !isLogin();
    }

    /**
     * 验证登录, 未登录则抛出异常
     */
    public static void checkLogin() {
        if (isNotLogin()) {
             throw new NotLoginException(RequestUtils.getRequest().getRequestURI());
        }
    }

    /**
     * 是否有访问权限, 有则返回 true
     */
    public static boolean isPermission() {
        // 没有登录当然也就表示没有权限了
        checkLogin();
        // 管理员直接放过权限检查
        if (isSuper()) return true;

        String url = RequestUtils.getRequest().getRequestURI();
        if (U.isBlank(url)) return false;

        String method = RequestUtils.getRequest().getMethod();
        if (U.isBlank(method)) return false;

        List<Resource> resources = getResource();
        if (A.isNotEmpty(resources)) {
            for (Resource resource : resources) {
                // 是否可以访问, 在 resource 类里面自解释
                if (resource.canRequest(url, method)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查权限, 无权限则抛出异常
     */
    public static void checkPermission() {
        if (!isPermission()) {
             throw new ForbiddenException("您没有当前 url(" + RequestUtils.getRequest().getRequestURL() + ") 的访问权限");
        }
    }
    /**获取数据源key**/
    public static String getDataSourceKey(HttpServletRequest request){
        if(request==null){
            request  = RequestUtils.getRequest();
        }
        String dataSourceKey = request.getHeader(U.DATA_SOURCE_KEY);
        if(U.isBlank(dataSourceKey)){
            dataSourceKey = request.getSession().getAttribute(U.DATA_SOURCE_KEY)+"";
        }
        if(U.isBlank(dataSourceKey)){
            dataSourceKey=request.getParameter(U.DATA_SOURCE_KEY);
            if(U.isBlank(dataSourceKey)){
                dataSourceKey = request.getAttribute(U.DATA_SOURCE_KEY)+"";
            }
        }
        return dataSourceKey;
    }


    public static String getUrl() {
        return RequestUtils.getRequest().getRequestURI();
    }

    public static String getPath() {
        return RequestUtils.getRequest().getServletPath();
    }

    public static String getReferer() {
        return RequestUtils.getRequest().getHeader("referer");
    }
}
