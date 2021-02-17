package com.learning.springboot.controller;

import com.learning.springboot.bean.Account;
import com.learning.springboot.bean.User;
import com.learning.springboot.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountsService accountsService;

    /**
     * 来登录页
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {
        if (!StringUtils.isEmpty(user.getUserName()) && "123456".equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户密码错误");
            return "login";
        }
    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model) {
        //Object loginUser = session.getAttribute("loginUser");
        //if (loginUser != null) {
        //    return "main";
        //} else {
        //    model.addAttribute("msg", "请登录");
        //    return "login";
        //}
        return "main";
    }

    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from accounts", Long.class);
        return aLong.toString();
    }

    @ResponseBody
    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable int id) {
        return accountsService.getAccountById(id);
    }
}
