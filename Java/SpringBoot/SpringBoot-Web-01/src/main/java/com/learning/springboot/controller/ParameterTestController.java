package com.learning.springboot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    @RequestMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam Integer age,
                                      @RequestParam List<String> inters,
                                      @RequestParam Map<String, String> params,
                                      @CookieValue("Idea-4ceeb189") String cookieValue,
                                      @CookieValue("Idea-4ceeb189") Cookie cookie) {

        Map<String, Object> map = new HashMap<>();

        //map.put("id", id);
        //map.put("name", name);
        //map.put("pv", pv);
        //map.put("userAgent", userAgent);
        //map.put("header", header);

        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        map.put("Idea-4ceeb189", cookieValue);
        map.put("cookie", cookie);

        return map;
    }

    @PostMapping("/save")
    public Map postMethod(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

}
