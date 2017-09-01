package com.wrpys.model;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private Date createDate;

    private Date createDate2;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate2() {
        return createDate2;
    }

    public void setCreateDate2(Date createDate2) {
        this.createDate2 = createDate2;
    }
}