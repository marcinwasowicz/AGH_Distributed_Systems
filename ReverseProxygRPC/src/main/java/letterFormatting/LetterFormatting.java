// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: letterformatting.proto

package letterFormatting;

public final class LetterFormatting {
  private LetterFormatting() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_letterFormatting_LetterFormatRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_letterFormatting_LetterFormatRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_letterFormatting_LetterFormatResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_letterFormatting_LetterFormatResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026letterformatting.proto\022\020letterFormatti" +
      "ng\"4\n\023LetterFormatRequest\022\017\n\007toUpper\030\001 \001" +
      "(\010\022\014\n\004text\030\002 \001(\t\"$\n\024LetterFormatResponse" +
      "\022\014\n\004text\030\001 \001(\t2s\n\017LetterFormatter\022`\n\rfor" +
      "matLetters\022%.letterFormatting.LetterForm" +
      "atRequest\032&.letterFormatting.LetterForma" +
      "tResponse\"\000B&\n\020letterFormattingB\020LetterF" +
      "ormattingP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_letterFormatting_LetterFormatRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_letterFormatting_LetterFormatRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_letterFormatting_LetterFormatRequest_descriptor,
        new java.lang.String[] { "ToUpper", "Text", });
    internal_static_letterFormatting_LetterFormatResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_letterFormatting_LetterFormatResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_letterFormatting_LetterFormatResponse_descriptor,
        new java.lang.String[] { "Text", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}