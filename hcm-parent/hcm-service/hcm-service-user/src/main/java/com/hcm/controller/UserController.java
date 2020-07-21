package com.hcm.controller;

import com.github.pagehelper.PageInfo;
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result<List<User>> search(@RequestBody User user) {
        List<User> users = userService.search(user);
        return Result.success(new StatusCode().OK, "条件查询成功", users);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success(new StatusCode().OK, "增加人员成功");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable("id") Integer id,@RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return Result.success(new StatusCode().OK, "修改人员成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return Result.success(new StatusCode().OK, "删除人员成功");
    }

    /**
     * 分页
     */
    @RequestMapping(value = "/findPage/{page}/{size}", method = RequestMethod.GET)
    public Result<PageInfo<User>> findPage(@PathVariable("page")Integer page, @PathVariable("size")Integer size) {
       PageInfo<User> pageInfo = userService.findPage(page, size);
       int q = 10/0;
       return Result.success(new StatusCode().OK, "分页查询成功", pageInfo);
    }

    /**
     * 分页，条件查询
     */
    @RequestMapping(value = "/findPageBySearch/{page}/{size}", method = RequestMethod.POST)
    public Result<PageInfo<User>> findPage(@RequestBody User user, @PathVariable("page")Integer page, @PathVariable("size")Integer size) {
        PageInfo<User> pageInfo = userService.findPageBySearch(user, page, size);
        return Result.success(new StatusCode().OK, "分页查询成功", pageInfo);
    }
}
