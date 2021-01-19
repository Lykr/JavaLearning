package com.learning.controller;

import com.learning.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    //localhost:8080/t1?name=xxx
    @GetMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model) {
        //接收前端参数
        System.out.println("接收到前端的参数为" + name);
        //返回结果到前端（Model）
        model.addAttribute("msg", name);
        //跳转视图
        return "/WEB-INF/test.jsp";
    }

    //前端接收对象
    @GetMapping("/t2")
    public String test2(User user, Model model) {
        System.out.println(user);
        return "/WEB-INF/test.jsp";
    }
}
