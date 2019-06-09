package com.qpf.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("demo")
    public Object demo() {
        Map<String, Object> map = new HashMap<>();

        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);

        return map;
    }

    @GetMapping("demo2")
    public String demo2() {
        return "{\"a\":1,\"b\":2,\"c\":3,\"d\":4}";
    }

}
