//package com.cczu.examples.rpc;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.ipc.RPC;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//
///**
// * @author yjz
// * @date 2021/12/19
// */
//public class HDFSClient {
//    public static void main(String[] args) throws IOException {
//        RPCProtocol client = RPC.getProxy(RPCProtocol.class, RPCProtocol.versionID, new InetSocketAddress("localhost", 8888), new Configuration());
//        client.mkdirs("/ssss");
//    }
//}
