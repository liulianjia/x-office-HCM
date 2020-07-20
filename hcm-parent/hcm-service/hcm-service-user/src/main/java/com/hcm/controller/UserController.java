package com.hcm.controller;

import com.hcm.service.UserService;
import com.hcm.user.pojo.User;
import entity.Result;
import entity.StatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
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
        return Result.success(new StatusCode().OK, "查询人员成功", users);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Result<User> findById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return Result.success(new StatusCode().OK, "根据ID查询成功", user);
    }
}
