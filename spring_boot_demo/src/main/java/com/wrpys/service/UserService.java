package com.wrpys.service;

import com.wrpys.model.User;

import java.util.Map;

/**
 * @author wrp
 * @Description com.wrpys.service.UserService
 * @Date 2017/7/19
 */
public interface UserService {

    User getUser(User user);

    void add(User user);

    Map<String, Object> list();
}
