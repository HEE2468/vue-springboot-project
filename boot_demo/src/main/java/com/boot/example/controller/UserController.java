package com.boot.example.controller;

import com.boot.example.entity.User;
import com.boot.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/findAllUsers", method = RequestMethod.POST)
    public String findAllUsers() {
       if(userService.findAllUsers().size()!=0){
           System.out.println("有数据!");
           return "YES";
       }else {
           System.out.println("没有数据!");
           return "NO";
       }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        User resultUser = userService.login(user);
        if(resultUser == null){
            return "failure";
        }
       return "success";
    }

}
