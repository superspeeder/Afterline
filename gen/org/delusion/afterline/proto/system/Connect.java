// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: afterline/system.proto

package org.delusion.afterline.proto.system;

/**
 * Protobuf type {@code afterline.Connect}
 */
public final class Connect extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:afterline.Connect)
    ConnectOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Connect.newBuilder() to construct.
  private Connect(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Connect() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Connect();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Connect(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 9: {
            bitField0_ |= 0x00000001;
            sessionId_ = input.readFixed64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_Connect_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_Connect_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.delusion.afterline.proto.system.Connect.class, org.delusion.afterline.proto.system.Connect.Builder.class);
  }

  private int bitField0_;
  public static final int SESSION_ID_FIELD_NUMBER = 1;
  private long sessionId_;
  /**
   * <code>optional fixed64 session_id = 1;</code>
   * @return Whether the sessionId field is set.
   */
  @java.lang.Override
  public boolean hasSessionId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>optional fixed64 session_id = 1;</code>
   * @return The sessionId.
   */
  @java.lang.Override
  public long getSessionId() {
    return sessionId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeFixed64(1, sessionId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeFixed64Size(1, sessionId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.delusion.afterline.proto.system.Connect)) {
      return super.equals(obj);
    }
    org.delusion.afterline.proto.system.Connect other = (org.delusion.afterline.proto.system.Connect) obj;

    if (hasSessionId() != other.hasSessionId()) return false;
    if (hasSessionId()) {
      if (getSessionId()
          != other.getSessionId()) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasSessionId()) {
      hash = (37 * hash) + SESSION_ID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getSessionId());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.delusion.afterline.proto.system.Connect parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.Connect parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.system.Connect parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.system.Connect parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.delusion.afterline.proto.system.Connect prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code afterline.Connect}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:afterline.Connect)
      org.delusion.afterline.proto.system.ConnectOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_Connect_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_Connect_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.delusion.afterline.proto.system.Connect.class, org.delusion.afterline.proto.system.Connect.Builder.class);
    }

    // Construct using org.delusion.afterline.proto.system.Connect.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      sessionId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_Connect_descriptor;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.system.Connect getDefaultInstanceForType() {
      return org.delusion.afterline.proto.system.Connect.getDefaultInstance();
    }

    @java.lang.Override
    public org.delusion.afterline.proto.system.Connect build() {
      org.delusion.afterline.proto.system.Connect result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.system.Connect buildPartial() {
      org.delusion.afterline.proto.system.Connect result = new org.delusion.afterline.proto.system.Connect(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.sessionId_ = sessionId_;
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.delusion.afterline.proto.system.Connect) {
        return mergeFrom((org.delusion.afterline.proto.system.Connect)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.delusion.afterline.proto.system.Connect other) {
      if (other == org.delusion.afterline.proto.system.Connect.getDefaultInstance()) return this;
      if (other.hasSessionId()) {
        setSessionId(other.getSessionId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.delusion.afterline.proto.system.Connect parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.delusion.afterline.proto.system.Connect) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long sessionId_ ;
    /**
     * <code>optional fixed64 session_id = 1;</code>
     * @return Whether the sessionId field is set.
     */
    @java.lang.Override
    public boolean hasSessionId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional fixed64 session_id = 1;</code>
     * @return The sessionId.
     */
    @java.lang.Override
    public long getSessionId() {
      return sessionId_;
    }
    /**
     * <code>optional fixed64 session_id = 1;</code>
     * @param value The sessionId to set.
     * @return This builder for chaining.
     */
    public Builder setSessionId(long value) {
      bitField0_ |= 0x00000001;
      sessionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional fixed64 session_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSessionId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      sessionId_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:afterline.Connect)
  }

  // @@protoc_insertion_point(class_scope:afterline.Connect)
  private static final org.delusion.afterline.proto.system.Connect DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.delusion.afterline.proto.system.Connect();
  }

  public static org.delusion.afterline.proto.system.Connect getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Connect>
      PARSER = new com.google.protobuf.AbstractParser<Connect>() {
    @java.lang.Override
    public Connect parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Connect(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Connect> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Connect> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.delusion.afterline.proto.system.Connect getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

