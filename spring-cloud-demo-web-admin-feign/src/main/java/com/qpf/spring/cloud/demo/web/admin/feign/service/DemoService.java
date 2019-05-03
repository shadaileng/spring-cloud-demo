package com.qpf.spring.cloud.demo.web.admin.feign.service;

import com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix.DemoServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-ADMIN", fallback = DemoServiceHystrix.class)
public interface DemoService {
    @GetMapping("demo")
    String demo(@RequestParam(value = "msg", required = false) String msg);
}
