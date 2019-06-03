package com.qpf.spring.cloud.sso.service.hystrix;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.sso.service.RedisCacheService;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheServiceHystrix implements RedisCacheService {

    @Override
    public String set(String key, Object value, long timeOut) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
