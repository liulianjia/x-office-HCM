package com.hcm.dao;

import com.github.pagehelper.PageInfo;
import com.hcm.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getUsers();

    public User findById(Integer id);

    public List<User> search(Object user);

    public void add(User user);

    public void update(User user);

    public void delete(Integer id);

    //分页
    public PageInfo<User> findPage(Integer page, Integer size);

    //分页，条件查询
    public PageInfo<User> findPageBySearch(User user,Integer page, Integer size);
}
