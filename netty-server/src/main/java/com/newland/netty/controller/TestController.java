package com.newland.netty.controller;

import com.newland.netty.entity.User;
import com.newland.netty.entity.common.PojoReq;
import com.newland.netty.server.PojoServer;
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
        PojoServer.clientServer.forEach((hostAndPort, channel) -> {
            System.out.println(hostAndPort);

            PojoReq req = new PojoReq();
            req.setReqId(System.currentTimeMillis());
            req.setServiceName("nettyCallBackServiceImpl");

            User user = new User();
            user.setUserId(2L);
            user.setUserName("ys");
            user.setAge(30);

            req.setParams(user);
            channel.writeAndFlush(req);
        });
    }

}
