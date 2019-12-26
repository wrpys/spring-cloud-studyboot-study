package com.newland.netty.entity;

import java.io.Serializable;

/**
 * @author WRP
 * @since 2019/12/25
 */
public class User implements Serializable {

    private static final long serialVersionUID = -3591122083723455151L;

    private Long userId;

    private String userName;

    private Integer age;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
