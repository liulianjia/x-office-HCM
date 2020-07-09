package com.hcm.controller;

import com.hcm.service.UserService;
import com.hcm.user.pojo.User;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin //跨域
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/getUsers", method= RequestMethod.GET)
    public Result<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        // 响应结果封装 Restful风格
        return Result.buildResult(StatusCode.OK,"查询用户成功",users);
    }
}
