package com.cc.eduservice.controller;

import com.cc.Result;
import com.cc.eduservice.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin //解决跨域
public class EduUserController {
    @PostMapping("login")
    public Result login(@RequestBody User user){
        return Result.ok().data("token", user.getUsername());
    }

    @GetMapping("/info")
    public Result info(String admin){
        return Result.ok()
                .data("roles", "['admin']")
                .data("introduction", "I am a super administrator")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .data("name", "Super CC");
    }

    @PostMapping("logout")
    public Result logout(String token){
        return Result.ok();
    }
}
