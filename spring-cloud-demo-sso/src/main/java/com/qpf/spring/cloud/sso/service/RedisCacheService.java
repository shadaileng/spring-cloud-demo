package com.qpf.spring.cloud.sso.service;

import com.qpf.spring.cloud.sso.service.hystrix.RedisCacheServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-REDIS", fallback = RedisCacheServiceHystrix.class)
public interface RedisCacheService {
    @PostMapping("/v1/api/set/{key}/{value}/{timeout}")
    String set(
            @PathVariable(value = "key") String key,
            @PathVariable(value = "value") Object value,
            @PathVariable(value = "timeout") long timeout) throws Exception;

    @GetMapping("/v1/api/get/{key}")
    String get(@PathVariable(value = "key") String key) throws Exception;
}
