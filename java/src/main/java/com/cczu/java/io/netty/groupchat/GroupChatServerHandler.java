package com.cczu.java.io.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    // 定义一个Channel组,管理所有channel
    //GlobalEventExecutor.INSTANCE 是一个单例的全局事件执行器
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        // 根据不同的情况，回送不同的消息
        channels.forEach(item->{
            if (channel != item){
                item.writeAndFlush("[客户] "+ channel.remoteAddress() + " 发送了消息 " +msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        // 关闭通道
        ctx.close();
    }

    /**
     * 表示连接建立，一旦连接，第一个被执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将客户端加入聊天的信息推送到其他在线的客户端
        // 该方法会将channelGroup中所有的channel遍历，并发送消息
        channels.writeAndFlush("【客户端】"+channel.remoteAddress()+" 加入群聊 \n" + dateFormatter.format(new Date()));
        channels.add(channel);
    }

    /**
     * 断开连接，将当前在线的channel推送离开的用户
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channels.writeAndFlush("【客户端】"+channel.remoteAddress()+" 离开群聊 \n");    }

    /**
     * 表示channel处于活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 上线了");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 离线了");

    }
}
