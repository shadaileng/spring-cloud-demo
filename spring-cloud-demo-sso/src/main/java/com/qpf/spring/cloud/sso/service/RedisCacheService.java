package com.qpf.spring.cloud.sso.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.sso.service.hystrix.DemoServiceHystrix;
import com.qpf.spring.cloud.sso.service.hystrix.RedisCacheServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
