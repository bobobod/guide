package com.cczu.java.io.netty.dubborpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyClient {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    private  static NettyClientHandler handler;

    private static void initClient() {
        handler = new NettyClientHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("MyStringDecoder", new StringDecoder());
                            pipeline.addLast("MyStringEncoder", new StringEncoder());
                            pipeline.addLast(handler);
                        }
                    });
            try {
                ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(7001)).sync();
                System.out.println(channelFuture.isSuccess());
                channelFuture.channel().closeFuture().sync();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public Object getBean(final Class<?> serviceClazz, final String providerName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{serviceClazz},
                ((proxy, method, args) -> {
                    if (handler == null) {
                        initClient();
                    }
                    // 设置要发给服务器的消息 args[0] 就是参数
                    handler.setPara(providerName + args[0]);
                    return executor.submit(handler).get();
                }));
    }
}
