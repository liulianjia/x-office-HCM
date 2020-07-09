package com.hcm.dao;

import com.hcm.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getUsers();
}
