package com.learning.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/meta-inf.jpg")
    public String hello() {
        return "hello";
    }
}
