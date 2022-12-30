package com.cczu.java.io.netty.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据
     * ChannelHandlerContext 上下文对象，含有管道pipeline,通道channel，地址
     * msg ,就是客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 比如这里有个非常耗费时间的业务 => 异步执行 => 提交到该channel对应的NioEventLoop的taskQueue中

        // 解决方案1 用户自定义普通任务 taskQueue
        ctx.channel().eventLoop().execute(() -> {
            try {
                // 会放入taskQueue中一一执行
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 xxxx execute", StandardCharsets.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("休眠十秒钟");
        });
        // 解决方案2 自定义定时任务 scheduledTaskQueue
        ctx.channel().eventLoop().schedule(() -> {
            try {
                // 会放入taskQueue中一一执行
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 xxxx schedule", StandardCharsets.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("休眠十秒钟");
        }, 5, TimeUnit.MINUTES);
        System.out.println("go on ...");
//        System.out.println("服务端读取线程 "+Thread.currentThread().getName());
//        System.out.println("server ctx:" + ctx);
//        System.out.println("看看channel 和 pipeline 之间的关系");
//        // pipeline 是个双向链表
//        System.out.println("channel ="+ctx.channel()+" pipeline="+ctx.pipeline());
//        // 将msg转成ByteBuf netty 独有的
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("客户端发送的消息是:" + buf.toString(StandardCharsets.UTF_8));
//        System.out.println("客户端地址:" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // writeAndFlush 是write+flush
        // 将数据写入缓冲区，并刷新
        // 对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 xxx2", StandardCharsets.UTF_8));

    }

    /**
     * 异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
