package com.qpf.spring.cloud.sso.service;

import com.qpf.spring.cloud.sso.service.hystrix.DemoServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-REDIS", fallback = DemoServiceHystrix.class)
public interface DemoService {
    @GetMapping("/v1/api/demo")
    String demo(@RequestParam(value = "msg", required = false) String msg);
    @GetMapping("/v1/api/demo/{msg}")
    String pathDemo(@PathVariable(value = "msg", required = true) String msg);
}
