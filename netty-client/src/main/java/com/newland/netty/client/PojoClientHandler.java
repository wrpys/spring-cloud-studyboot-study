package com.newland.netty.client;

import com.newland.netty.entity.common.PojoMsg;
import com.newland.netty.entity.common.PojoReq;
import com.newland.netty.service.NettyCallBackService;
import com.newland.netty.utils.SpringContextUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * 客户端接收消息处理
 *
 * @author WRP
 * @since 2019/12/25
 */
@ChannelHandler.Sharable
@Component
public class PojoClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        LOGGER.info("handler channelActive,{}", ctx.name());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("接收到的数据:{}", msg);
        // 接收到的消息数据为PojoReq，执行方法
        if (msg != null && msg instanceof PojoReq) {
            exec((PojoReq) msg);
        } else {
            LOGGER.info("发送的数据不支持...");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        LOGGER.error("handler channelReadComplete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("handler exceptionCaught", cause);
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 执行回调方法
     *
     * @param pojoReq
     * @return
     */
    private void exec(PojoReq pojoReq) {
        PojoMsg pojoMsg = null;
        try {
            Object callBack = SpringContextUtil.getBean(pojoReq.getServiceName());
            if (callBack instanceof NettyCallBackService) {
                LOGGER.info("exec 默认公共回调...");
                ((NettyCallBackService) callBack).exec(pojoReq.getParams());
            } else {
                LOGGER.info("exec 自定义回调...");
                callBack.getClass().getDeclaredMethod(pojoReq.getMethodName(), Object.class).invoke(callBack, pojoReq.getParams());
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException", e);
            pojoMsg = new PojoMsg("IllegalAccessException", e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException", e);
            pojoMsg = new PojoMsg("InvocationTargetException", e.getMessage());
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException", e);
            pojoMsg = new PojoMsg("NoSuchMethodException", e.getMessage());
        }
        // 有错误消息，默认错误处理
        if (pojoMsg != null) {
            PojoReq resp = new PojoReq();
            resp.setReqId(System.currentTimeMillis());
            resp.setServiceName("errorNettyCallBackService");
            resp.setParams(pojoMsg);
            PojoClient.nettyServer.writeAndFlush(resp);
        }
    }

}
