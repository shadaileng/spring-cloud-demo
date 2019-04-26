package com.qpf.spring.cloud.demo.web.admin.ribbon.controller;

import com.qpf.spring.cloud.demo.web.admin.ribbon.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;
    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return demoService.demo(msg);
    }
}
