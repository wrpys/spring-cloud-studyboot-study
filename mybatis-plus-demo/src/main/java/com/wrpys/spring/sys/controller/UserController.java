package com.wrpys.spring.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wrpys.spring.sys.entity.User;
import com.wrpys.spring.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author WRP
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("findAll")
    public List<User> findAll() {
        return userService.list();
    }

    @GetMapping("save")
    public void save() {
        User user = new User();
        user.setName("name_1");
        user.setAge(11);
        user.setEmail("11@baomidou.com");
        userService.save(user);

//        List<User> users = new ArrayList<>();
//        user = new User();
//        user.setName("name_2");
//        user.setAge(12);
//        user.setEmail("12@baomidou.com");
//        users.add(user);
//        user = new User();
//        user.setName("name_3");
//        user.setAge(13);
//        user.setEmail("13@baomidou.com");
//        users.add(user);
//        user = new User();
//        user.setName("name_4");
//        user.setAge(14);
//        user.setEmail("14@baomidou.com");
//        users.add(user);
//        userService.saveBatch(users);
//
//        userService.saveOrUpdate(user, new Wrapper<User>() {
//            @Override
//            public User getEntity() {
//                User whereUser = new User();
//                whereUser.setName("name_5");
//                return whereUser;
//            }
//
//            @Override
//            public MergeSegments getExpression() {
//                return null;
//            }
//
//            @Override
//            public String getSqlSegment() {
//                return null;
//            }
//        });
    }

    @GetMapping("update")
    public void update() {
        User user = new User();
        user.setId(17L);
        user.setAge(20);
        userService.updateById(user);
    }

    @GetMapping("list")
    public List<User> page() {
        return userService.list(null);
    }

    @GetMapping("page2")
    public IPage<User> page2() {
        Page<User> page = new Page<>();
        page.setCurrent(1L);
        page.setPages(10L);

        User user = new User();
        user.setName("name");

        return userService.selectPage(page, user);
    }

}

