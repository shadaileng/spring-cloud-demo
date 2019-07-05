package com.qpf.spring.cloud.demo.web.admin.feign.controller;

import com.qpf.spring.cloud.demo.web.admin.feign.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return demoService.demo(msg);
    }
}
