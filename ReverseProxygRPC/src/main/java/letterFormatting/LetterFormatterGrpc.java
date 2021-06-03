package letterFormatting;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: letterformatting.proto")
public final class LetterFormatterGrpc {

  private LetterFormatterGrpc() {}

  public static final String SERVICE_NAME = "letterFormatting.LetterFormatter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<letterFormatting.LetterFormatRequest,
      letterFormatting.LetterFormatResponse> getFormatLettersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "formatLetters",
      requestType = letterFormatting.LetterFormatRequest.class,
      responseType = letterFormatting.LetterFormatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<letterFormatting.LetterFormatRequest,
      letterFormatting.LetterFormatResponse> getFormatLettersMethod() {
    io.grpc.MethodDescriptor<letterFormatting.LetterFormatRequest, letterFormatting.LetterFormatResponse> getFormatLettersMethod;
    if ((getFormatLettersMethod = LetterFormatterGrpc.getFormatLettersMethod) == null) {
      synchronized (LetterFormatterGrpc.class) {
        if ((getFormatLettersMethod = LetterFormatterGrpc.getFormatLettersMethod) == null) {
          LetterFormatterGrpc.getFormatLettersMethod = getFormatLettersMethod =
              io.grpc.MethodDescriptor.<letterFormatting.LetterFormatRequest, letterFormatting.LetterFormatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "formatLetters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  letterFormatting.LetterFormatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  letterFormatting.LetterFormatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LetterFormatterMethodDescriptorSupplier("formatLetters"))
              .build();
        }
      }
    }
    return getFormatLettersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LetterFormatterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LetterFormatterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LetterFormatterStub>() {
        @java.lang.Override
        public LetterFormatterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LetterFormatterStub(channel, callOptions);
        }
      };
    return LetterFormatterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LetterFormatterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LetterFormatterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LetterFormatterBlockingStub>() {
        @java.lang.Override
        public LetterFormatterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LetterFormatterBlockingStub(channel, callOptions);
        }
      };
    return LetterFormatterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LetterFormatterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LetterFormatterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LetterFormatterFutureStub>() {
        @java.lang.Override
        public LetterFormatterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LetterFormatterFutureStub(channel, callOptions);
        }
      };
    return LetterFormatterFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class LetterFormatterImplBase implements io.grpc.BindableService {

    /**
     */
    public void formatLetters(letterFormatting.LetterFormatRequest request,
        io.grpc.stub.StreamObserver<letterFormatting.LetterFormatResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFormatLettersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFormatLettersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                letterFormatting.LetterFormatRequest,
                letterFormatting.LetterFormatResponse>(
                  this, METHODID_FORMAT_LETTERS)))
          .build();
    }
  }

  /**
   */
  public static final class LetterFormatterStub extends io.grpc.stub.AbstractAsyncStub<LetterFormatterStub> {
    private LetterFormatterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LetterFormatterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LetterFormatterStub(channel, callOptions);
    }

    /**
     */
    public void formatLetters(letterFormatting.LetterFormatRequest request,
        io.grpc.stub.StreamObserver<letterFormatting.LetterFormatResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFormatLettersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LetterFormatterBlockingStub extends io.grpc.stub.AbstractBlockingStub<LetterFormatterBlockingStub> {
    private LetterFormatterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LetterFormatterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LetterFormatterBlockingStub(channel, callOptions);
    }

    /**
     */
    public letterFormatting.LetterFormatResponse formatLetters(letterFormatting.LetterFormatRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFormatLettersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LetterFormatterFutureStub extends io.grpc.stub.AbstractFutureStub<LetterFormatterFutureStub> {
    private LetterFormatterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LetterFormatterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LetterFormatterFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<letterFormatting.LetterFormatResponse> formatLetters(
        letterFormatting.LetterFormatRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFormatLettersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FORMAT_LETTERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LetterFormatterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LetterFormatterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FORMAT_LETTERS:
          serviceImpl.formatLetters((letterFormatting.LetterFormatRequest) request,
              (io.grpc.stub.StreamObserver<letterFormatting.LetterFormatResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LetterFormatterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LetterFormatterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return letterFormatting.LetterFormatting.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LetterFormatter");
    }
  }

  private static final class LetterFormatterFileDescriptorSupplier
      extends LetterFormatterBaseDescriptorSupplier {
    LetterFormatterFileDescriptorSupplier() {}
  }

  private static final class LetterFormatterMethodDescriptorSupplier
      extends LetterFormatterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LetterFormatterMethodDescriptorSupplier(String methodName) {
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
      synchronized (LetterFormatterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LetterFormatterFileDescriptorSupplier())
              .addMethod(getFormatLettersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
