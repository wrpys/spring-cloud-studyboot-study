package com.newland.netty.controller;

import com.newland.netty.client.PojoClient;
import com.newland.netty.entity.User;
import com.newland.netty.entity.common.PojoReq;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WRP
 * @since 2019/12/25
 */
@RestController
public class TestController {

    @GetMapping("send")
    public void send() {
        PojoReq req = new PojoReq();
        req.setReqId(System.currentTimeMillis());
        req.setServiceName("nettyCallBackServiceImpl");
        req.setMethodName("exec");

        User user = new User();
        user.setUserId(1L);
        user.setUserName("wrp");
        user.setAge(28);

        req.setParams(user);
        PojoClient.nettyServer.writeAndFlush(req);

        req = new PojoReq();
        req.setReqId(System.currentTimeMillis());

        user = new User();
        user.setUserId(1L);
        user.setUserName("wrp");
        user.setAge(28);

        req.setParams(user);
        req.setServiceName("myCallBackService");
        req.setMethodName("test");
        PojoClient.nettyServer.writeAndFlush(req);

        req = new PojoReq();
        req.setReqId(System.currentTimeMillis());

        user = new User();
        user.setUserId(4L);
        user.setUserName("wjb");
        user.setAge(51);

        req.setParams(user);
        req.setServiceName("testCallBackService");
        req.setMethodName("test");
        PojoClient.nettyServer.writeAndFlush(req);

    }

}
