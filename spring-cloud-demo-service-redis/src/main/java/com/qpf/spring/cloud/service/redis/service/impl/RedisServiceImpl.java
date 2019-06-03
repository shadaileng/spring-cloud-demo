package com.qpf.spring.cloud.service.redis.service.impl;

import com.qpf.spring.cloud.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class RedisServiceImpl implements RedisService {

    private RedisTemplate redisTemplate;

    @Autowired
    public RedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, Object value, long timeOut) {
        redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
}
