package com.newland.netty.server;

import com.newland.netty.entity.common.PojoMsg;
import com.newland.netty.entity.common.PojoReq;
import com.newland.netty.service.NettyCallBackService;
import com.newland.netty.utils.SpringContextUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;

/**
 * 服务端接收处理
 *
 * @author WRP
 * @since 2019/12/25
 */
@ChannelHandler.Sharable
@Component
public class PojoServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        LOGGER.info("handler channelActive begin...");
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        LOGGER.info("hostName:{},port{}", inetSocketAddress.getHostName(), inetSocketAddress.getPort());
        PojoServer.clientServer.put(inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort(), ctx.channel());
        PojoServer.clientServer.forEach((k, v) ->
                LOGGER.info("channel:{}", k)
        );
        LOGGER.info("handler channelActive end...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("接收到的数据:{}", msg);
        // 接收到的消息数据为PojoReq，执行方法
        if (msg != null && msg instanceof PojoReq) {
            exec(ctx.channel(), (PojoReq) msg);
        } else {
            LOGGER.info("发送的数据不支持...");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.info("exceptionCaught 网络异常，关闭网络 begin");
        LOGGER.error("exceptionCaught 网络异常，关闭网络", cause);
        cause.printStackTrace();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        PojoServer.clientServer.remove(inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort());
        ctx.close();
        PojoServer.clientServer.forEach((k, v) ->
                LOGGER.info("channel:{}", k)
        );
        LOGGER.info("exceptionCaught 网络异常，关闭网络 end");
    }

    /**
     * 执行回调方法
     *
     * @param channel
     * @param pojoReq
     * @return
     */
    private void exec(Channel channel, PojoReq pojoReq) {
        PojoMsg pojoMsg = null;
        try {
            Object callBack = SpringContextUtil.getBean(pojoReq.getServiceName());
            PojoServer.channelThreadLocal.set(channel);
            if (callBack != null) {
                if (callBack instanceof NettyCallBackService) {
                    LOGGER.info("exec 默认实现公共回调接口...");
                    ((NettyCallBackService) callBack).exec(pojoReq.getParams());
                } else {
                    LOGGER.info("exec 自定义回调...");
                    callBack.getClass().getDeclaredMethod(pojoReq.getMethodName(), Object.class).invoke(callBack, pojoReq.getParams());
                }
            } else {
                pojoMsg = new PojoMsg("no_bean", "spring上下文未找到实例");
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
            channel.writeAndFlush(resp);
        }
    }

}
