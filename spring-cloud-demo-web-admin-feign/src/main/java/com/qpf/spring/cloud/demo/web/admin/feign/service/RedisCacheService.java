package com.qpf.spring.cloud.demo.web.admin.feign.service;

import com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix.RedisCacheServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-REDIS", fallback = RedisCacheServiceHystrix.class)
public interface RedisCacheService {
    @PostMapping("/v1/api/set/{key}/{value}/{timeout}")
    public String set(
            @PathVariable(value = "key", required = true) String key,
            @PathVariable(value = "value", required = true) Object value,
            @PathVariable(value = "timeout", required = true) long timeout) throws Exception;

    @GetMapping("/v1/api/get/{key}")
    public String get(@PathVariable(value = "key", required = true) String key) throws Exception;
}
