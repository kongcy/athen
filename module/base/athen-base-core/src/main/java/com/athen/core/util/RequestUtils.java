package com.athen.core.util;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * !!此工具类请只在 controller 中调用!!
 */
public final class RequestUtils {

    /**
     * 获取真实客户端IP
     * 关于 X-Forwarded-For 参考: http://zh.wikipedia.org/wiki/X-Forwarded-For<br>
     * 这一 HTTP 头一般格式如下:
     * X-Forwarded-For: client1, proxy1, proxy2,<br><br>
     * 其中的值通过一个 逗号 + 空格 把多个 IP 地址区分开, 最左边(client1)是最原始客户端的IP地址,
     * 代理服务器每成功收到一个请求，就把请求来源IP地址添加到右边
     */
    public static String getRealIp() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("X-Real-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("X-Forwarded-For");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP
            return ip.split(",")[0].trim();
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("HTTP_CLIENT_IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("X-Cluster-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        return request.getRemoteAddr();
    }

    /**
     * 判断当前请求是否是 ajax 请求, 是 ajax 则返回 true
     */
    public static boolean isAjaxRequest() {
        HttpServletRequest request = getRequest();
        String requestedWith = request.getHeader("X-Requested-With");
        String contentType = request.getHeader("Content-Type");
        return (requestedWith != null && "XMLHttpRequest".equals(requestedWith))
                || (contentType != null && MediaType.APPLICATION_JSON_VALUE.startsWith(contentType))
                || request.getParameter("ajax") != null || request.getParameter("json") != null;
    }

    public static String userAgent() {
        return getRequest().getHeader("User-Agent");
    }

    /**
     * 请求来源(PC, iOS, Android, Other)
     */
    public static String getOsTypeWithStr() {
        return osType().name();
    }

    private static OsType osType() {
        String userAgent = userAgent();

        if (U.checkRegexWithRelax(userAgent, "(?i)iP(hone|od|ad)")) {
            return OsType.iOS;
        }
        if (U.checkRegexWithRelax(userAgent, "(?i)Mobile|okhttp|Android")) {
            return OsType.Android;
        }
        if (U.checkRegexWithRelax(userAgent, "(?i)AppleWebKit|Mozilla|Chrome|Safari|MSIE|Windows NT")) {
            return OsType.PC;
        }
        return OsType.Other;
    }

    /**
     * 注册来源(1.pc, 2.iOS, 3.安卓, 4.其他)
     */
    static enum OsType {
        PC(1), iOS(2), Android(3), Other(4);
        int code;

        OsType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * 请求来源(1.pc, 2.iOS, 3.安卓, 4.其他)
     */
    public static int getOsType() {
        return osType().code;
    }

    /**
     * 判断当前请求是否来自移动端, 来自移动端则返回 true
     */
    public static boolean isMobileRequest() {
        return U.checkMobile(userAgent());
    }

    /**
     * 格式化参数, 如果是文件流(form 表单中有 type="multipart/form-data" 这种), 则不打印出参数
     *
     * @return 示例: id=xxx&name=yyy
     */
    public static String formatParam() {
        HttpServletRequest request = getRequest();
        return ServletFileUpload.isMultipartContent(request)
                ? "(content-type start with multipart/) uploading file"
                : U.formatParam(request.getParameterMap());
    }

    /**
     * 返回 url 并且拼上参数, 非 get 请求将忽略参数
     */
    public static String getUrl() {
        HttpServletRequest request = getRequest();
        String url = request.getRequestURL().toString();
        if (HttpMethod.GET.equals(request.getMethod())) {
            String param = formatParam();
            if (U.isNotBlank(param)) {
                url = (U.appendUrl(url) + param);
            }
        }
        return url;
    }

    /**
     * 格式化头里的参数
     *
     * @return 示例: id=xxx&name=yyy
     */
    public static String formatHeadParam() {
        HttpServletRequest request = getRequest();
        StringBuilder sbd = new StringBuilder();
        int i = 0;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            if (i > 0) sbd.append("\n");
            String headName = headerNames.nextElement();
            sbd.append(headName).append(" : ").append(request.getHeader(headName));
            i++;
        }
        return sbd.toString();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

}
