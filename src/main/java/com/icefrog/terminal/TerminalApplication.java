/*
 * Copyright 2019 icefrog.su@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icefrog.terminal;

import com.github.pagehelper.PageHelper;
import com.icefrog.terminal.interceptor.LoginedInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
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
@MapperScan(basePackages = "com.icefrog.terminal.mapper")
public class TerminalApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
    
    @Autowired
    private LoginedInterceptor loginedInterceptor;
    
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
        return builder.sources(TerminalApplication.class);
    }
    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("120MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("120MB");
        return factory.createMultipartConfig();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginedInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/system/authcode")
                .excludePathPatterns("/system/login")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/menu-icons/**")
                .excludePathPatterns("/image/**");
        
    }
    
    /**
     * 配置Mybatis的分页插件PageHelper
     *
     * @link https://github.com/pagehelper/pagehelper-spring-boot
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
