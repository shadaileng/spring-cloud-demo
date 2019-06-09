package com.qpf.spring.cloud.service.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Value("${server.port}")
    private String port;

    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return String.format("msg: %s, port: %s", msg, port);
    }
}
