package com.cczu.examples.hdfs.rpc.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: NameNodeRpcService.proto")
public final class NameNodeServiceGrpc {

  private NameNodeServiceGrpc() {}

  public static final String SERVICE_NAME = "com.cczu.examples.hdfs.rpc.NameNodeService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.cczu.examples.hdfs.rpc.model.RegisterRequest,
      com.cczu.examples.hdfs.rpc.model.RegisterResponse> METHOD_REGISTER =
      io.grpc.MethodDescriptor.<com.cczu.examples.hdfs.rpc.model.RegisterRequest, com.cczu.examples.hdfs.rpc.model.RegisterResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.cczu.examples.hdfs.rpc.NameNodeService", "register"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.RegisterRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.RegisterResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.cczu.examples.hdfs.rpc.model.HeartbeatRequest,
      com.cczu.examples.hdfs.rpc.model.HeartbeatResponse> METHOD_HEARTBEAT =
      io.grpc.MethodDescriptor.<com.cczu.examples.hdfs.rpc.model.HeartbeatRequest, com.cczu.examples.hdfs.rpc.model.HeartbeatResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.cczu.examples.hdfs.rpc.NameNodeService", "heartbeat"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.HeartbeatRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.HeartbeatResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.cczu.examples.hdfs.rpc.model.MkdirRequest,
      com.cczu.examples.hdfs.rpc.model.MkdirResponse> METHOD_MKDIR =
      io.grpc.MethodDescriptor.<com.cczu.examples.hdfs.rpc.model.MkdirRequest, com.cczu.examples.hdfs.rpc.model.MkdirResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.cczu.examples.hdfs.rpc.NameNodeService", "mkdir"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.MkdirRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.MkdirResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.cczu.examples.hdfs.rpc.model.ShutdownRequest,
      com.cczu.examples.hdfs.rpc.model.ShutdownResponse> METHOD_SHUTDOWN =
      io.grpc.MethodDescriptor.<com.cczu.examples.hdfs.rpc.model.ShutdownRequest, com.cczu.examples.hdfs.rpc.model.ShutdownResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.cczu.examples.hdfs.rpc.NameNodeService", "shutdown"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.ShutdownRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.ShutdownResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest,
      com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse> METHOD_FETCH_EDITS_LOG =
      io.grpc.MethodDescriptor.<com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest, com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.cczu.examples.hdfs.rpc.NameNodeService", "fetchEditsLog"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NameNodeServiceStub newStub(io.grpc.Channel channel) {
    return new NameNodeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NameNodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NameNodeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NameNodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NameNodeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NameNodeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(com.cczu.examples.hdfs.rpc.model.RegisterRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.RegisterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER, responseObserver);
    }

    /**
     */
    public void heartbeat(com.cczu.examples.hdfs.rpc.model.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.HeartbeatResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_HEARTBEAT, responseObserver);
    }

    /**
     */
    public void mkdir(com.cczu.examples.hdfs.rpc.model.MkdirRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.MkdirResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MKDIR, responseObserver);
    }

    /**
     */
    public void shutdown(com.cczu.examples.hdfs.rpc.model.ShutdownRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.ShutdownResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SHUTDOWN, responseObserver);
    }

    /**
     */
    public void fetchEditsLog(com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FETCH_EDITS_LOG, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REGISTER,
            asyncUnaryCall(
              new MethodHandlers<
                com.cczu.examples.hdfs.rpc.model.RegisterRequest,
                com.cczu.examples.hdfs.rpc.model.RegisterResponse>(
                  this, METHODID_REGISTER)))
          .addMethod(
            METHOD_HEARTBEAT,
            asyncUnaryCall(
              new MethodHandlers<
                com.cczu.examples.hdfs.rpc.model.HeartbeatRequest,
                com.cczu.examples.hdfs.rpc.model.HeartbeatResponse>(
                  this, METHODID_HEARTBEAT)))
          .addMethod(
            METHOD_MKDIR,
            asyncUnaryCall(
              new MethodHandlers<
                com.cczu.examples.hdfs.rpc.model.MkdirRequest,
                com.cczu.examples.hdfs.rpc.model.MkdirResponse>(
                  this, METHODID_MKDIR)))
          .addMethod(
            METHOD_SHUTDOWN,
            asyncUnaryCall(
              new MethodHandlers<
                com.cczu.examples.hdfs.rpc.model.ShutdownRequest,
                com.cczu.examples.hdfs.rpc.model.ShutdownResponse>(
                  this, METHODID_SHUTDOWN)))
          .addMethod(
            METHOD_FETCH_EDITS_LOG,
            asyncUnaryCall(
              new MethodHandlers<
                com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest,
                com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse>(
                  this, METHODID_FETCH_EDITS_LOG)))
          .build();
    }
  }

  /**
   */
  public static final class NameNodeServiceStub extends io.grpc.stub.AbstractStub<NameNodeServiceStub> {
    private NameNodeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameNodeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NameNodeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameNodeServiceStub(channel, callOptions);
    }

    /**
     */
    public void register(com.cczu.examples.hdfs.rpc.model.RegisterRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.RegisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void heartbeat(com.cczu.examples.hdfs.rpc.model.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.HeartbeatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_HEARTBEAT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mkdir(com.cczu.examples.hdfs.rpc.model.MkdirRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.MkdirResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MKDIR, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void shutdown(com.cczu.examples.hdfs.rpc.model.ShutdownRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.ShutdownResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SHUTDOWN, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fetchEditsLog(com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest request,
        io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FETCH_EDITS_LOG, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NameNodeServiceBlockingStub extends io.grpc.stub.AbstractStub<NameNodeServiceBlockingStub> {
    private NameNodeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameNodeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NameNodeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameNodeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.cczu.examples.hdfs.rpc.model.RegisterResponse register(com.cczu.examples.hdfs.rpc.model.RegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER, getCallOptions(), request);
    }

    /**
     */
    public com.cczu.examples.hdfs.rpc.model.HeartbeatResponse heartbeat(com.cczu.examples.hdfs.rpc.model.HeartbeatRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_HEARTBEAT, getCallOptions(), request);
    }

    /**
     */
    public com.cczu.examples.hdfs.rpc.model.MkdirResponse mkdir(com.cczu.examples.hdfs.rpc.model.MkdirRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MKDIR, getCallOptions(), request);
    }

    /**
     */
    public com.cczu.examples.hdfs.rpc.model.ShutdownResponse shutdown(com.cczu.examples.hdfs.rpc.model.ShutdownRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SHUTDOWN, getCallOptions(), request);
    }

    /**
     */
    public com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse fetchEditsLog(com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FETCH_EDITS_LOG, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NameNodeServiceFutureStub extends io.grpc.stub.AbstractStub<NameNodeServiceFutureStub> {
    private NameNodeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameNodeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NameNodeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameNodeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cczu.examples.hdfs.rpc.model.RegisterResponse> register(
        com.cczu.examples.hdfs.rpc.model.RegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cczu.examples.hdfs.rpc.model.HeartbeatResponse> heartbeat(
        com.cczu.examples.hdfs.rpc.model.HeartbeatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_HEARTBEAT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cczu.examples.hdfs.rpc.model.MkdirResponse> mkdir(
        com.cczu.examples.hdfs.rpc.model.MkdirRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MKDIR, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cczu.examples.hdfs.rpc.model.ShutdownResponse> shutdown(
        com.cczu.examples.hdfs.rpc.model.ShutdownRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SHUTDOWN, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse> fetchEditsLog(
        com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FETCH_EDITS_LOG, getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_HEARTBEAT = 1;
  private static final int METHODID_MKDIR = 2;
  private static final int METHODID_SHUTDOWN = 3;
  private static final int METHODID_FETCH_EDITS_LOG = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NameNodeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NameNodeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((com.cczu.examples.hdfs.rpc.model.RegisterRequest) request,
              (io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.RegisterResponse>) responseObserver);
          break;
        case METHODID_HEARTBEAT:
          serviceImpl.heartbeat((com.cczu.examples.hdfs.rpc.model.HeartbeatRequest) request,
              (io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.HeartbeatResponse>) responseObserver);
          break;
        case METHODID_MKDIR:
          serviceImpl.mkdir((com.cczu.examples.hdfs.rpc.model.MkdirRequest) request,
              (io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.MkdirResponse>) responseObserver);
          break;
        case METHODID_SHUTDOWN:
          serviceImpl.shutdown((com.cczu.examples.hdfs.rpc.model.ShutdownRequest) request,
              (io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.ShutdownResponse>) responseObserver);
          break;
        case METHODID_FETCH_EDITS_LOG:
          serviceImpl.fetchEditsLog((com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest) request,
              (io.grpc.stub.StreamObserver<com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class NameNodeServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return NameNodeServer.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NameNodeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NameNodeServiceDescriptorSupplier())
              .addMethod(METHOD_REGISTER)
              .addMethod(METHOD_HEARTBEAT)
              .addMethod(METHOD_MKDIR)
              .addMethod(METHOD_SHUTDOWN)
              .addMethod(METHOD_FETCH_EDITS_LOG)
              .build();
        }
      }
    }
    return result;
  }
}
