package com.athen.system.config;

import com.athen.core.converter.*;
import com.athen.core.json.JsonResult;
import com.athen.core.json.JsonUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 */
@Configuration
public class ManagerWarInit implements WebMvcConfigurer {
    /**
     * 分页默认页
     */
    public static final int DEFAULT_PAGE_NO = 1;
    /**
     * 分页默认的每页条数
     */
    public static final int DEFAULT_LIMIT = 15;
    /**
     * 前台传递过来的分页参数名
     */
    public static final String GLOBAL_PAGE = "page";
    /**
     * 前台传递过来的每页条数名
     */
    public static final String GLOBAL_LIMIT = "limit";

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 从前台过来的数据转换成对应类型的转换器
        registry.addConverter(new StringTrimAndEscapeConverter());
        registry.addConverterFactory(new StringToNumberConverter());
        registry.addConverterFactory(new StringToEnumConverter());
        registry.addConverter(new String2DateConverter());
        registry.addConverter(new String2MoneyConverter());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 默认转换器注册后, 插入自定义的请求响应转换器
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converters.add(new MappingJackson2HttpMessageConverter(JsonUtil.RENDER));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 如果 Controller 方法中的参数有 PageBounds 则从前台获取数据组装, 如果没有传递则给设置一个默认值
        argumentResolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return PageBounds.class.isAssignableFrom(parameter.getParameterType());
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                          NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
                // 判断数据是否合理, 不合理就给定默认值
                int page = NumberUtils.toInt(request.getParameter(GLOBAL_PAGE));
                if (page <= 0) page = DEFAULT_PAGE_NO;
                int limit = NumberUtils.toInt(request.getParameter(GLOBAL_LIMIT));
                if (limit <= 0) limit = DEFAULT_LIMIT;
                return new PageBounds(page, limit);
            }
        });
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        // 如果 Controller 方法的返回值是 JsonResult 则返回 json, 不需要额外在方法或类上标注 @ResponseBody
        returnValueHandlers.add(new HandlerMethodReturnValueHandler() {
            @Override
            public boolean supportsReturnType(MethodParameter returnType) {
                return JsonResult.class.isAssignableFrom(returnType.getParameterType());
            }

            @Override
            public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                          ModelAndViewContainer mav, NativeWebRequest request) throws Exception {
                mav.setRequestHandled(true);
                ((JsonResult) returnValue).toJson(request.getNativeResponse(HttpServletResponse.class));
            }
        });
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义的拦截器
        registry.addInterceptor(new ManagerInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }
    /***
     @Override public void addCorsMappings(CorsRegistry registry) {
     // 开启 cors filter 解决跨域问题. 在 nginx 中配置要添加一堆的 if 判断域名, 没有这里灵活
     registry.addMapping("/**")
     .allowedOrigins("*") // Access-Control-Allow-Origin: 允许向当前服务器提交请求的 url
     .allowCredentials(true) // Access-Control-Allow-Credentials: 响应是否可以被得到
     .allowedHeaders("*") // Access-Control-Allow-Headers: 指明在实际的请求中, 可以使用的自定义 http 请求头
     // .maxAge(123) // Access-Control-Max-Age: 请求结果的有效期时间是多少, 单位秒
     // .exposedHeaders(Const.GLOBAL_TRACK_ID) // Access-Control-Expose-Headers: 允许获取头信息里面的白名单
     .allowedMethods(Const.SUPPORT_METHODS); // Access-Control-Allow-Methods: 资源可以被请求的方式
     }**/
}
