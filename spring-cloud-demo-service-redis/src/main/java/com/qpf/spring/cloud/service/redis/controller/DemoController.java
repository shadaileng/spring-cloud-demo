package com.qpf.spring.cloud.service.redis.controller;

import com.qpf.spring.cloud.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api")
public class DemoController {

    private RedisService redisService;

    @Autowired
    public DemoController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return String.format("msg: %s, port: %s", msg, port);
    }
    @GetMapping("demo/{msg}")
    public String pathDemo(@PathVariable(value = "msg", required = true) String msg) {
        return String.format("msg: %s, port: %s", msg, port);
    }
}
