package com.hcm.service.impl;

import com.hcm.dao.UserMapper;
import com.hcm.service.UserService;
import com.hcm.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        List<User> users = userMapper.getUsers();
        return users;
    }
}
