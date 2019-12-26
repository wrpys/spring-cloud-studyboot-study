package com.newland.netty.service.impl;

import com.newland.netty.entity.User;
import com.newland.netty.entity.common.PojoReq;
import com.newland.netty.server.PojoServer;
import com.newland.netty.service.MyCallBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author WRP
 * @since 2019/12/25
 */
@Service("myCallBackService")
public class MyCallBackServiceImpl implements MyCallBackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCallBackServiceImpl.class);

    @Override
    public void test(Object params) {
        LOGGER.info("MyCallBackServiceImpl.test,params:{}", params);

        PojoReq req = new PojoReq();
        req.setReqId(System.currentTimeMillis());
        req.setServiceName("nettyCallBackServiceImpl");

        User user = new User();
        user.setUserId(3L);
        user.setUserName("wmx");
        user.setAge(3);

        req.setParams(user);
        PojoServer.channelThreadLocal.get().writeAndFlush(req);

    }
}
