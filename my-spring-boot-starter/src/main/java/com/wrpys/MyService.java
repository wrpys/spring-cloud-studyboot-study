package com.wrpys;

/**
 * @author wrp
 * @Description com.wrpys.MyService
 * @Date 2017/7/19
 */
public class MyService {

    private String msg;

    public String sayHello() {
        return "Hello " + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
