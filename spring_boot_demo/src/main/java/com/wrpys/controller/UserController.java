package com.wrpys.controller;

import com.alibaba.fastjson.JSON;
import com.wrpys.MyService;
import com.wrpys.model.User;
import com.wrpys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wrp
 * @Description com.wrpys.controller.UserController
 * @Date 2017/7/19
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyService myService;

    @RequestMapping(value = "list")
    public String list() {
//        return JSON.toJSONString(myService.sayHello());
        return JSON.toJSONString(userService.list());
    }

    @GetMapping(value = "list2/{userId}")
    public String list2(@PathVariable(value = "userId") Integer userId) {
        User userParam = new User();
        userParam.setUserId(userId);
        User user = userService.getUser(userParam);
        return JSON.toJSONString(user);
    }

    @GetMapping(value = "add")
    public String list2(User user) {
        userService.add(user);
        return JSON.toJSONString(user);
    }

}
