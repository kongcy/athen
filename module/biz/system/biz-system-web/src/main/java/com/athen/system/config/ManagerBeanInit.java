package com.athen.system.config;
import com.athen.core.util.RenderDomain;
import com.athen.core.util.RenderViewResolver;
import com.athen.core.util.SpringBeanFactory;
import com.athen.system.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/** 项目中需要额外加载的类 */
@Configuration
public class ManagerBeanInit {

    @Value("${online:false}")
    private boolean online;

    /** freemarker 的默认配置 */
    @Autowired
    private FreeMarkerProperties properties;

//   @Autowired
//    private RenderDomain domain;

    /** 域名相关 */
    @Bean
    @ConfigurationProperties(prefix = "domain")
    public RenderDomain getDomain() {
        return new RenderDomain();
    }

    @Bean
    public SpringBeanFactory setupApplicationContext() {
        return new SpringBeanFactory();
    }

    /** 处理字符的 filter. */
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(StandardCharsets.UTF_8.displayName());
        encodingFilter.setForceEncoding(true);
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<CharacterEncodingFilter>();
        registrationBean.setFilter(encodingFilter);
        return registrationBean;
    }


    /**
     * 覆盖默认的 viewResolver<br>
     * 参考: https://qbgbook.gitbooks.io/spring-boot-reference-guide-zh/content/IX.%20%E2%80%98How-to%E2%80%99%20guides/65.8.%20Customize%20ViewResolvers.html
     *
     * @see org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration
     */
    @Bean(name = "freeMarkerViewResolver")
    public RenderViewResolver viewResolver() {
        RenderViewResolver resolver = new RenderViewResolver().putVariableInContext(online, getDomain());
        Map<String,Object> map = resolver.getAttributesMap();
        map.put(SessionUtil.class.getSimpleName(), new SessionUtil());
        properties.applyToMvcViewResolver(resolver);
        return resolver;
    }


    /*@Bean
    public FilterRegistrationBean siteFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ConfigurableSiteMeshFilter() {
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
                builder.addDecoratorPath("/main", "/layouts/default.ftl")
                        .addDecoratorPath("/system*//*", "/layouts/default.ftl")
                        .addDecoratorPath("/third*//*", "/layouts/default.ftl")
                        .addDecoratorPath("/order*//*", "/layouts/default.ftl")
                        .addDecoratorPath("/finance*//*", "/layouts/default.ftl")
                        .addDecoratorPath("/advice*//*", "/layouts/default.ftl")
                        // 上面是要渲染的, 下面是放过的
                        .addExcludedPath("/login")
                        .addExcludedPath("/error")
                        .addExcludedPath("/logout")
                        .addExcludedPath("/manager*//*")
                        .addExcludedPath("/api*//*");
            }
        });
        filterRegistrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return filterRegistrationBean;
    }*/
}
