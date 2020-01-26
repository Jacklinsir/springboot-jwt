package com.jacklinsir.springbootjwt.config;

import com.jacklinsir.springbootjwt.Interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author linSir
 * @version V1.0
 * @Date 2020/1/26 19:46
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor())
                //拦截自定路径
                .addPathPatterns("/**")
                //放行指定路径
                .excludePathPatterns("/user/login");
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }
}
