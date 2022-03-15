package com.natley.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
