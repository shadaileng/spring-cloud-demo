package com.qpf.spring.cloud.sso.controller;

import com.qpf.spring.cloud.sso.service.DemoService;
import com.qpf.spring.cloud.sso.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    private DemoService demoService;

    private RedisCacheService redisCacheService;

    @Autowired
    public DemoController(DemoService demoService, RedisCacheService redisCacheService) {
        this.demoService = demoService;
        this.redisCacheService = redisCacheService;
    }

    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return demoService.demo(msg);
    }
    @GetMapping("path-demo")
    public String pathDemo(@RequestParam(value = "msg", required = true) String msg) {
        return demoService.pathDemo(msg);
    }

    @PostMapping("set/{key}/{value}/{timeout}")
    public String set(@PathVariable(value = "key", required = true) String key,
                      @PathVariable(value = "value", required = true) String value,
                      @PathVariable(value = "timeout", required = true) long timeout) throws Exception {
        String result = redisCacheService.set(key, value, timeout);
        System.out.println(result);

        return result;
    }
    @GetMapping("get/{key}")
    public String get(@PathVariable(value = "key", required = true) String key) throws Exception {
        String result = redisCacheService.get(key);
        System.out.println(result);

        return result;
    }
}
