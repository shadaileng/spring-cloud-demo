package com.qpf.spring.cloud.demo.web.admin.feign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DispatcherController {

    @GetMapping({"", "list"})
    public String list() {
        return "list";
    }
}
