package com.cczu.java.io.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //
        if (evt instanceof IdleStateEvent){
            // 将 evt转型为 IdleStateEvent
            IdleStateEvent event = (IdleStateEvent) evt;
            String str = null;
            IdleState state = event.state();
            switch (state){
                case READER_IDLE:
                    str= "读空闲";
                    break;
                case WRITER_IDLE:
                    str="写空闲";
                    break;
                case ALL_IDLE:
                    str="读写空闲";
                    break;
                default: str = "";
            }
            System.out.println(ctx.channel().remoteAddress()+"-----"+str);
        }
    }
}
