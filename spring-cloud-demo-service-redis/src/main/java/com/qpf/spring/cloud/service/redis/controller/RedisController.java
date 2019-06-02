package com.qpf.spring.cloud.service.redis.controller;

import com.qpf.spring.cloud.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    private RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("set/{key}/{value}/{timeOut}")
    public boolean set(
            @PathVariable(value = "key", required = true) String key,
            @PathVariable(value = "value", required = true) Object value,
            @PathVariable(value = "timeOut", required = true) long timeOut) {
        redisService.set(key, value, timeOut);
        return true;
    }

    @GetMapping("get/{key}")
    public String get(@PathVariable(value = "key", required = true) String key) {
        return (String) redisService.get(key);
    }
}
