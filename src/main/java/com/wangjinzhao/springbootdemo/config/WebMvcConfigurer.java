package com.wangjinzhao.springbootdemo.config;

import com.wangjinzhao.springbootdemo.web.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by WANGJINZHAO on 2018/1/18.
 * 或是继承WebMvcConfigurationSupport 也能实现mvc的配置，且支持配置更全
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor())
                .addPathPatterns("/api/**");
        //registry.addInterceptor(new CommonInterceptor());//默认拦截所有请求
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(false)
                .favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(false)
                .useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
