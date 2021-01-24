package com.learning.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.learning.pojo.User;
import com.learning.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
@RestController //所有方法都不走视图解析器
public class UserController {

    @RequestMapping("/j1")
//    @ResponseBody //不走视图解析器，会直接返回一个字符串
    public String json1() throws JsonProcessingException {
        //jackson 中的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //创建对象
        User user = new User("测试", 23, "man");

        String res = mapper.writeValueAsString(user);
        System.out.println(res);

        return res;
    }

    @RequestMapping("/j2")
//    @ResponseBody //不走视图解析器，会直接返回一个字符串
    public String json2() throws JsonProcessingException {
        //jackson 中的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<User> list = new ArrayList<>();

        //创建对象
        User user1 = new User("测试1", 23, "man");
        User user2 = new User("测试2", 23, "man");
        User user3 = new User("测试3", 23, "man");
        User user4 = new User("测试4", 23, "man");

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        String res = mapper.writeValueAsString(list);
        System.out.println(res);

        return res;
    }

    @RequestMapping("/j3")
//    @ResponseBody //不走视图解析器，会直接返回一个字符串
    public String json3() throws JsonProcessingException {
        Date date = new Date();

        return JsonUtil.getJson(date);
    }

    @RequestMapping("/j4")
//    @ResponseBody //不走视图解析器，会直接返回一个字符串
    public String json4() throws JsonProcessingException {
        List<User> list = new ArrayList<>();

        //创建对象
        User user1 = new User("测试1", 23, "man");
        User user2 = new User("测试2", 23, "man");
        User user3 = new User("测试3", 23, "man");
        User user4 = new User("测试4", 23, "man");

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);



        return JSON.toJSONString(list);
    }
}
