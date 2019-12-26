package com.newland.netty.entity.common;

import java.io.Serializable;

/**
 * 执行返回信息
 *
 * @author WRP
 * @since 2019/12/26
 */
public class PojoMsg implements Serializable {

    private static final long serialVersionUID = -1811469306601409356L;

    private String code;

    private String msg;

    public PojoMsg() {
    }

    public PojoMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PojoMsg{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
