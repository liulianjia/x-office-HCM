package com.hcm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hcm.dao.UserMapper;
import com.hcm.service.UserService;
import com.hcm.user.pojo.User;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userMapper.getUsers();
        return users;
    }

    @Override
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        return user;
    }

    /**
     * 多条件查询
     * @param user
     * @return
     */
    @Override
    public List<User> search(User user) {
        Example example = createExample(user);
        return userMapper.search(example);
    }

    /**
     * 条件构造
     * @param user
     * @return
     */
    public Example createExample(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(user != null) {
            if(!StringUtil.isEmpty(user.getName())) {
                /**
                 * 1.user的属性名
                 * 2.占位符参数
                 */
                criteria.andLike("name", "%"+user.getName()+"%");
            }
            if(!StringUtil.isEmpty(user.getPassword())) {
                criteria.andEqualTo("password", user.getPassword());
            }
        }
        return example;
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<User> findPage(Integer page, Integer size) {
        /**
         * page:当前页
         * size:每页记录数
         */
        PageHelper.startPage(page, size);

        //查询集合
        List<User> users = userMapper.getUsers();
        //封装PageInfo
        return new PageInfo<User>(users);
    }

    /**
     * 分页，条件查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<User> findPageBySearch(User user, Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);
        //查询
        Example example = createExample(user);
        List<User> users = userMapper.search(example);
        //封装PageInfo<User>
        return new PageInfo<User>(users);
    }
}
