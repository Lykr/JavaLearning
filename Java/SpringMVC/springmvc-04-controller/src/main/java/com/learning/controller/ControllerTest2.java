package com.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //代表被 Spring 接管，备注界的类的所有方法如果返回值是 String，并且有具体的页面可以跳转，就会被视图解析器解析
public class ControllerTest2 {
    @RequestMapping("/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "test2");
        return "/WEB-INF/test.jsp"; //返回的是视图的页面名称
    }

    @RequestMapping("/t3")
    public String test3(Model model) {
        model.addAttribute("msg", "test3");
        return "/WEB-INF/test.jsp"; //返回的是视图的页面名称
    }
}
