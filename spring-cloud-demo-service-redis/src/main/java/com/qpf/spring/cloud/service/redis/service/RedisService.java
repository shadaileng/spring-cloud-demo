package com.qpf.spring.cloud.service.redis.service;

public interface RedisService {
    void set(String key, Object value, long timeOut);
    Object get(String key);
}
