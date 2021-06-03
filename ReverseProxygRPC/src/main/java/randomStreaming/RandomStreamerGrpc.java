package randomStreaming;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: randomstreaming.proto")
public final class RandomStreamerGrpc {

  private RandomStreamerGrpc() {}

  public static final String SERVICE_NAME = "randomStreaming.RandomStreamer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<randomStreaming.RandomStreamRequest,
      randomStreaming.NextRandom> getGetRandomStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRandomStream",
      requestType = randomStreaming.RandomStreamRequest.class,
      responseType = randomStreaming.NextRandom.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<randomStreaming.RandomStreamRequest,
      randomStreaming.NextRandom> getGetRandomStreamMethod() {
    io.grpc.MethodDescriptor<randomStreaming.RandomStreamRequest, randomStreaming.NextRandom> getGetRandomStreamMethod;
    if ((getGetRandomStreamMethod = RandomStreamerGrpc.getGetRandomStreamMethod) == null) {
      synchronized (RandomStreamerGrpc.class) {
        if ((getGetRandomStreamMethod = RandomStreamerGrpc.getGetRandomStreamMethod) == null) {
          RandomStreamerGrpc.getGetRandomStreamMethod = getGetRandomStreamMethod =
              io.grpc.MethodDescriptor.<randomStreaming.RandomStreamRequest, randomStreaming.NextRandom>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getRandomStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  randomStreaming.RandomStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  randomStreaming.NextRandom.getDefaultInstance()))
              .setSchemaDescriptor(new RandomStreamerMethodDescriptorSupplier("getRandomStream"))
              .build();
        }
      }
    }
    return getGetRandomStreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RandomStreamerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RandomStreamerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RandomStreamerStub>() {
        @java.lang.Override
        public RandomStreamerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RandomStreamerStub(channel, callOptions);
        }
      };
    return RandomStreamerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RandomStreamerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RandomStreamerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RandomStreamerBlockingStub>() {
        @java.lang.Override
        public RandomStreamerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RandomStreamerBlockingStub(channel, callOptions);
        }
      };
    return RandomStreamerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RandomStreamerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RandomStreamerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RandomStreamerFutureStub>() {
        @java.lang.Override
        public RandomStreamerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RandomStreamerFutureStub(channel, callOptions);
        }
      };
    return RandomStreamerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RandomStreamerImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<randomStreaming.RandomStreamRequest> getRandomStream(
        io.grpc.stub.StreamObserver<randomStreaming.NextRandom> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getGetRandomStreamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRandomStreamMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                randomStreaming.RandomStreamRequest,
                randomStreaming.NextRandom>(
                  this, METHODID_GET_RANDOM_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class RandomStreamerStub extends io.grpc.stub.AbstractAsyncStub<RandomStreamerStub> {
    private RandomStreamerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RandomStreamerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RandomStreamerStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<randomStreaming.RandomStreamRequest> getRandomStream(
        io.grpc.stub.StreamObserver<randomStreaming.NextRandom> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getGetRandomStreamMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class RandomStreamerBlockingStub extends io.grpc.stub.AbstractBlockingStub<RandomStreamerBlockingStub> {
    private RandomStreamerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RandomStreamerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RandomStreamerBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class RandomStreamerFutureStub extends io.grpc.stub.AbstractFutureStub<RandomStreamerFutureStub> {
    private RandomStreamerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RandomStreamerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RandomStreamerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_RANDOM_STREAM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RandomStreamerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RandomStreamerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RANDOM_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getRandomStream(
              (io.grpc.stub.StreamObserver<randomStreaming.NextRandom>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RandomStreamerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RandomStreamerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return randomStreaming.RandomStreamingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RandomStreamer");
    }
  }

  private static final class RandomStreamerFileDescriptorSupplier
      extends RandomStreamerBaseDescriptorSupplier {
    RandomStreamerFileDescriptorSupplier() {}
  }

  private static final class RandomStreamerMethodDescriptorSupplier
      extends RandomStreamerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RandomStreamerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RandomStreamerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RandomStreamerFileDescriptorSupplier())
              .addMethod(getGetRandomStreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
