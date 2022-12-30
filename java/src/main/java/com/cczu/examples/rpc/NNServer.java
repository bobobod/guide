//package com.cczu.examples.rpc;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.ipc.RPC;
//
//import java.io.IOException;
//
///**
// * @author yjz
// * @date 2021/12/19
// */
//public class NNServer implements RPCProtocol {
//    public static void main(String[] args) throws IOException {
//        RPC.Server rpc = new RPC.Builder(new Configuration()).setBindAddress("localhost")
//                .setPort(8888)
//                .setProtocol(RPCProtocol.class)
//                .setInstance(new NNServer())
//                .build();
//        rpc.start();
//        System.out.println("服务端启动了");
//    }
//
//    @Override
//    public void mkdirs(String path) {
//        System.out.println("服务端接受到了客户端请求" + path);
//    }
//}
