// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: randomstreaming.proto

package randomStreaming;

public final class RandomStreamingProto {
  private RandomStreamingProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_randomStreaming_RandomStreamRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_randomStreaming_RandomStreamRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_randomStreaming_NextRandom_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_randomStreaming_NextRandom_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025randomstreaming.proto\022\017randomStreaming" +
      "\"J\n\023RandomStreamRequest\022\r\n\005bound\030\001 \001(\005\022\r" +
      "\n\005count\030\002 \001(\005\022\025\n\rtime_interval\030\003 \001(\005\"\032\n\n" +
      "NextRandom\022\014\n\004next\030\001 \001(\0052l\n\016RandomStream" +
      "er\022Z\n\017getRandomStream\022$.randomStreaming." +
      "RandomStreamRequest\032\033.randomStreaming.Ne" +
      "xtRandom\"\000(\0010\001B)\n\017randomStreamingB\024Rando" +
      "mStreamingProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_randomStreaming_RandomStreamRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_randomStreaming_RandomStreamRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_randomStreaming_RandomStreamRequest_descriptor,
        new java.lang.String[] { "Bound", "Count", "TimeInterval", });
    internal_static_randomStreaming_NextRandom_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_randomStreaming_NextRandom_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_randomStreaming_NextRandom_descriptor,
        new java.lang.String[] { "Next", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}