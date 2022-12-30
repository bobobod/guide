package com.cczu.java.io.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class GroupChatClient {
    private final int port;
    private final String host;

    public GroupChatClient(int port, String host) {
        this.port = port;
        this.host = host;
    }
    private void run() throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("MyStringDecoder", new StringDecoder());
                            pipeline.addLast("MyStringEncoder", new StringEncoder());
                            pipeline.addLast("GroupChatClientHandler",new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            Channel channel = channelFuture.channel();
            System.out.println("--------"+ channel.remoteAddress());
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String next = scanner.next();
                channel.writeAndFlush(next);
            }
            channelFuture.channel().closeFuture().sync();

        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GroupChatClient client = new GroupChatClient(7000, "localhost");
        client.run();
    }
}
