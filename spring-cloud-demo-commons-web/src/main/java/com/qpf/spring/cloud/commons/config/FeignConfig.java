package com.qpf.spring.cloud.commons.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    private static int connectTimeOutMillis = 120000;
    private static int readTimeOutMillis = 120000;
    @Bean
    public Request.Options options() {
        System.out.println("===================FeignConfig============================");
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }
}
