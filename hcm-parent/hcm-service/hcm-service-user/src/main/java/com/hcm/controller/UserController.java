package com.hcm.controller;

import com.hcm.service.UserService;
import com.hcm.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin //跨域
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/getUsers", method= RequestMethod.GET)
    public List<User> getUsers(){
        List<User> users = userService.getUsers();
        return users;
    }
}
