package com.cczu.java.io.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


public class NioHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    // 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // HttpObject 客户端和服务端相互通讯的数据
        if (msg instanceof HttpRequest){
            System.out.println(msg.getClass());
            System.out.println("客户端地址："+ctx.channel().remoteAddress());
            // 回复信息给浏览器
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello client", CharsetUtil.UTF_8);
            // 构建一个http请求
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            ctx.writeAndFlush(response);

        }
    }
}
