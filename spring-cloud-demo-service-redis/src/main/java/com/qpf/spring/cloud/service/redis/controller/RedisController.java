package com.qpf.spring.cloud.service.redis.controller;

import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
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
    public String set(
            @PathVariable(value = "key", required = true) String key,
            @PathVariable(value = "value", required = true) Object value,
            @PathVariable(value = "timeOut", required = true) long timeOut) throws Exception {
        redisService.set(key, value, timeOut);
        return JsonUtils.obj2json(BaseResult.OK());
    }

    @GetMapping("get/{key}")
    public String get(@PathVariable(value = "key", required = true) String key) throws Exception {
        BaseResult result = BaseResult.OK(redisService.get(key));
        return JsonUtils.obj2json(result);
    }
}
