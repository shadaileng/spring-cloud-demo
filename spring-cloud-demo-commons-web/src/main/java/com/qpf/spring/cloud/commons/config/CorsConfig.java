package com.qpf.spring.cloud.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//            .allowedOrigins("*")
//            .allowedMethods("GET", "POST", "PUT", "OPTIONS")
//            .allowedHeaders(
////                    "Content-Type", "X-Requested-With",
//                    "accept","Origin",
//                    "Accept-Control-Request-Method", "Accept-Control-Request-Headers")
//            .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//            .allowCredentials(true).maxAge(3600);
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "OPTIONS");
        System.out.println("======================addCorsMappings=========================");
    }
}
