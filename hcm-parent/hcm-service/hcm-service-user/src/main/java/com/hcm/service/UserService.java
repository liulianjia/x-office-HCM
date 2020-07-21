package com.hcm.service;

import com.github.pagehelper.PageInfo;
import com.hcm.user.pojo.User;
import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public User findById(Integer id);

    public List<User> search(User user);

    public void add(User user);

    public void update(User user);

    public void delete(Integer id);

    //分页
    public PageInfo<User> findPage(Integer page, Integer size);

    //分页，条件查询
    public PageInfo<User> findPageBySearch(User user,Integer page, Integer size);
}
