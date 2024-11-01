package com.colorfill.colorfill.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @RequestMapping("/")  // ルートへこのメソッドをマップする
    public String test() {
        return "Hello Wakuto";
    }
}
