package com.newland.netty.service.impl;

import com.newland.netty.entity.User;
import com.newland.netty.entity.common.PojoReq;
import com.newland.netty.server.PojoServer;
import com.newland.netty.service.NettyCallBackService;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author WRP
 * @since 2019/12/25
 */
@Service("nettyCallBackServiceImpl")
public class NettyCallBackServiceImpl implements NettyCallBackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyCallBackServiceImpl.class);

    /**
     * 执行方法
     *
     * @param params
     * @return
     */
    @Override
    public void exec(Object params) {
        LOGGER.info("NettyCallBackServiceImpl.exec,params:{}", params);

        if (params instanceof User) {
            User user = (User) params;
            user.setUserName("TEST");

            PojoReq pojoReq = new PojoReq();
            pojoReq.setReqId(System.currentTimeMillis());
            pojoReq.setServiceName("nettyCallBackServiceImpl");
            pojoReq.setMethodName("exec");
            pojoReq.setParams(user);

            Channel channel = PojoServer.channelThreadLocal.get();
            channel.writeAndFlush(pojoReq);
        }
    }
}
