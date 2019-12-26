package com.newland.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Netty客户端连接
 *
 * @author WRP
 * @since 2019/12/25
 */
@Configuration
public class PojoClient implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoClient.class);

    /**
     * 连接的服务端
     */
    public static Channel nettyServer = null;

    @Value("${netty.host:127.0.0.1}")
    private String host;

    @Value("${netty.port:5656}")
    private Integer port;

    @Value("${netty.fail.timeOut:30}")
    private Integer timeOut;

    @Value("${netty.fail.retry-number:5}")
    private Integer retryNumber;

    private Integer currentRetryNumber = 1;


    @Autowired
    private PojoClientHandler pojoClientHandler;

    @Override
    public void run(String... args) throws Exception {
        connect();
    }

    public void connect() {
        LOGGER.info("netty client connecting...");

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ObjectDecoder(1024 * 1024,
                                    ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                            ch.pipeline().addLast(new ObjectEncoder());
                            ch.pipeline().addLast(pojoClientHandler);
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            if (f.isSuccess()) {
                LOGGER.info("netty client connect success...");
            } else {
                LOGGER.info("netty client connect error...");
            }
            nettyServer = f.channel();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error("Exception", e);
        } finally {
            group.shutdownGracefully();
            try {
                if (currentRetryNumber <= retryNumber) {
                    LOGGER.info("连接失败，{}S后开始第{}重试...", timeOut * currentRetryNumber, currentRetryNumber);
                    TimeUnit.SECONDS.sleep(timeOut * currentRetryNumber);
                    currentRetryNumber++;
                    connect();
                }
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException", e);
            }
        }

    }

}
