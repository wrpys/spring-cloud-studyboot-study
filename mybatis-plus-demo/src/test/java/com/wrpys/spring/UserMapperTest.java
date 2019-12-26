package com.wrpys.spring;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wrpys.spring.mybatis.MybatisPlusConfig;
import com.wrpys.spring.sys.entity.User;
import com.wrpys.spring.sys.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

/**
 * @author WRP
 * @since 2019/12/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));

        MybatisPlusConfig.dynamicTableName.set("sys_user_2019");

        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * 列名直接出现在java代码，个人不建议使用
     */
    @Test
    public void testSelectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "name_").lt("age", 14);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

        queryWrapper.select("name", "email").like("name", "name_").lt("age", 14);
        userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

        queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class, info -> !Objects.equals(info.getColumn(), "create_time") && !Objects.equals(info.getColumn(), "update_time")).like("name", "name_");
        userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

}
