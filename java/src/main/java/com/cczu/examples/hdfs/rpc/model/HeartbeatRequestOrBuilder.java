// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NameNodeRpcModel.proto

package com.cczu.examples.hdfs.rpc.model;

public interface HeartbeatRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.cczu.examples.hdfs.rpc.HeartbeatRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string ip = 1;</code>
   * @return The ip.
   */
  String getIp();
  /**
   * <code>string ip = 1;</code>
   * @return The bytes for ip.
   */
  com.google.protobuf.ByteString
      getIpBytes();

  /**
   * <code>string hostname = 2;</code>
   * @return The hostname.
   */
  String getHostname();
  /**
   * <code>string hostname = 2;</code>
   * @return The bytes for hostname.
   */
  com.google.protobuf.ByteString
      getHostnameBytes();
}