package com.learning.springboot.controller;

import com.learning.springboot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {

    @GetMapping("/basic_table")
    public String basicTable() {
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamicTable(Model model) {
        List<User> users = Arrays.asList(new User("User1", "123456"),
                new User("User2", "123456"),
                new User("User3", "123456"),
                new User("User4", "123456"));
        model.addAttribute("users", users);

        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsiveTable() {
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editableTable() {
        return "table/editable_table";
    }
}
