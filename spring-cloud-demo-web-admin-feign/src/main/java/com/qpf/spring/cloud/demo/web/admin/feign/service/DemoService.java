package com.qpf.spring.cloud.demo.web.admin.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("SPRING-CLOUD-DEMO-SERVICE-ADMIN")
public interface DemoService {
    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg);
}
