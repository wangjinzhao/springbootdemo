package com.wangjinzhao.springbootdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by WANGJINZHAO on 2018/1/18.
 *
 */
@Configuration
@PropertySource(value = {"classpath:api.properties"}, ignoreResourceNotFound = false)
@Slf4j
public class PropertyConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        if (log.isDebugEnabled()) {
            log.debug("placeHolderConfigurer init properties!!!");
        }
        final PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return placeholderConfigurer;
    }

}
