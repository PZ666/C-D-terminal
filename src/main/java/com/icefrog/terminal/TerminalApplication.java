/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

/***
 * Report Terminal
 * 
 * SpringBoot(version 2.0.1) Application
 *
 * @author icefrog.su@qq.com
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages = "com.zoomgo.terminal.mapper")
public class TerminalApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
    
    public static void main(String[] args) {
        SpringApplication.run(TerminalApplication.class, args);
    }
    
    /**
     * {@inheritDoc} <br>
     *
     * It is mainly used to package the project as a war and deploy it to tomcat
     * when it is deployed
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootApplication.class);
    }
    
    /**
     * 配置Mybatis的分页插件PageHelper
     *
     * @see https://github.com/pagehelper/pagehelper-spring-boot
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
