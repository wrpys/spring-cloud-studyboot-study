package com.wrpys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wrpys.dao.UserMapper;
import com.wrpys.model.User;
import com.wrpys.service.UserService;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wrp
 * @Description com.wrpys.service.impl.UserServiceImpl
 * @Date 2017/7/19
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(User user) {
        return userMapper.selectByPrimaryKey(user.getUserId());
    }

    @Override
    @Transactional
    public void add(User user) {
        userMapper.insert(user);
        int i = 10/0;
    }

    @Override
    public Map<String, Object> list() {
        Page<User> page = PageHelper.startPage(0, 3);
        List<User> users = userMapper.selectByParams(new User());
        Map<String ,Object> result = new HashMap<>();
        result.put("total", page.getTotal());
        result.put("rows", users);
        return result;
    }
}
