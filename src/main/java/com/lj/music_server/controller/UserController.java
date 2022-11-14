package com.lj.music_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @RequestMapping("login")
    public String login() {
        return "user/login";
    }
    @RequestMapping("register")
    public String register() {
        return "user/register";
    }
}
