package com.hcm.service;

import com.hcm.user.pojo.User;
import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public User findById(Integer id);

    public void add(User user);

    public void update(User user);

    public void delete(Integer id);
}
