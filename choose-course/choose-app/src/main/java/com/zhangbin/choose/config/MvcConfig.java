package com.zhangbin.choose.config;

import com.zhangbin.choose.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置类
 * 配置MVC视图
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            // 添加拦截路径
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor()).
                        excludePathPatterns("/", "/user/login", "/login", "/error",
                                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/favicon.ico",
                                "/swagger-ui.html", "/swagger-resources/configuration/ui", "/swagger-resources",
                                "/v2/api-docs", "/swagger-resources/configuration/security",
                                "/webjars/springfox-swagger-ui/fonts/**.ttf", "/webjars/springfox-swagger-ui/images/**").
                        addPathPatterns("/**");
                super.addInterceptors(registry);
            }

            // 设置登录 HTML与默认页面
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("/user/login");
                registry.addViewController("/login.html").setViewName("/user/login");
                super.addViewControllers(registry);
            }
        };
    }
}
