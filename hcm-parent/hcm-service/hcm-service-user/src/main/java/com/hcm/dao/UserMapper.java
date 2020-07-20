package com.hcm.dao;

import com.hcm.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getUsers();

    public User findById(Integer id);

    public void add(User user);

    public void update(User user);

    public void delete(Integer id);
}
