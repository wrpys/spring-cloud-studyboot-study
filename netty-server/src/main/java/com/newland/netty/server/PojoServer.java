package com.newland.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * Netty服务端启动
 *
 * @author WRP
 * @since 2019/12/25
 */
@Configuration
public class PojoServer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoServer.class);

    /**
     * 连接的client端
     * Map<host:port, Channel>
     */
    public static Map<String, Channel> clientServer = new HashMap<>();

    /**
     * 当前线程上下文的Channel
     */
    public static NamedThreadLocal<Channel> channelThreadLocal = new NamedThreadLocal<>("CHANNEL");

    @Value("${netty.port:5656}")
    private Integer port;

    @Autowired
    private PojoServerHandler pojoServerHandler;

    @Override
    public void run(String... args) throws Exception {
        bind();
    }

    public void bind() throws Exception {

        LOGGER.info("netty server starting...");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ObjectDecoder(1024 * 1024,
                                            ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
                                    .addLast(new ObjectEncoder())
                                    .addLast(pojoServerHandler);
                        }
                    });

            ChannelFuture f = b.bind(port).sync();
            LOGGER.info("netty server wait for connect...");
            f.channel().closeFuture().sync();
        } finally {
            LOGGER.info("netty server wait for connect  Error!...");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
