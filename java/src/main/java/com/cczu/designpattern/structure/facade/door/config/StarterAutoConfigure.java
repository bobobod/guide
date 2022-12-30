package com.cczu.designpattern.structure.facade.door.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yjz
 * @date 2021/11/14
 */
@Configuration
@ConditionalOnClass(StarterService.class)
@EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfigure {
    /*
    @ConditionalOnBean
    仅仅在当前上下文中存在某个对象时，才会实例化一个Bean
    @ConditionalOnClass
    某个class位于类路径上，才会实例化一个Bean
    @ConditionalOnExpression
    当表达式为true的时候，才会实例化一个Bean
    @ConditionalOnMissingBean
    仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean
    @ConditionalOnMissingClass
    某个class类路径上不存在的时候，才会实例化一个Bean
     */

    @Autowired
    private StarterServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "it.door", value = "enabled", havingValue = "true")
    public StarterService starterService() {
        return new StarterService(properties.getUserStr());
    }

}
