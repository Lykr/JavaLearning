package com.learning.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestController {

    @GetMapping("/learning")
    public String learning(Model model) {
        model.addAttribute("msg", "Hello world!");
        model.addAttribute("link", "http://www.baidu.com");
        return "success";
    }
}
