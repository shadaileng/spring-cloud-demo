package com.qpf.spring.cloud.sso.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.sso.service.hystrix.RedisCacheServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-REDIS", fallback = RedisCacheServiceHystrix.class)
public interface RedisCacheService {
    @PostMapping("set/{key}/{value}/{timeOut}")
    public String set(
            @PathVariable(value = "key", required = true) String key,
            @PathVariable(value = "value", required = true) Object value,
            @PathVariable(value = "timeOut", required = true) long timeOut);

    @GetMapping("get/{key}")
    public String get(@PathVariable(value = "key", required = true) String key);
}
