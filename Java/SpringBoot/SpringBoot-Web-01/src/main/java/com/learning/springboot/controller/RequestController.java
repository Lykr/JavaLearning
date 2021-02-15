package com.learning.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功！");
        request.setAttribute("code", 200);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request) {

        Object msg1 = request.getAttribute("msg");
        Object code1 = request.getAttribute("code");

        Map<String, Object> map = new HashMap<>();

        map.put("annotation_msg", msg);
        map.put("annotation_code", code);
        map.put("request_msg", msg1);
        map.put("request_code", code1);

        return map;
    }

    /**
     * 1. SpringBoot 默认禁用矩阵变量功能
     *   1.1. 自定义一个 WebMvcConfigurer 组件，并设置自定义 UrlPathHelper
     * 2. 矩阵变量要放在 URL 路径变量里
     */
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brands") List<String> brands,
                        @PathVariable("path") String path) {

        Map<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brands", brands);
        map.put("path", path);

        return map;
    }

    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(name = "age", pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(name = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();

        map.put("bossAge", bossAge);
        map.put("empAge", empAge);

        return map;
    }
}
