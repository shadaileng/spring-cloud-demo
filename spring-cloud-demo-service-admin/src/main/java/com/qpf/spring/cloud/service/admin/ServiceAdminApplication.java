package com.qpf.spring.cloud.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
@ComponentScan({"com.qpf.spring.cloud.service.admin", "com.qpf.spring.cloud.commons"})
@MapperScan(basePackages = "com.qpf.spring.cloud.commons.mapper")
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
