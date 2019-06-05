package com.qpf.spring.cloud.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrixDashboard
// 服务消费者
@EnableFeignClients
@EnableDiscoveryClient
// 服务提供者
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.qpf.spring.cloud", "com.qpf.spring.cloud.commons.mapper"})
public class SSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class, args);
    }
}
