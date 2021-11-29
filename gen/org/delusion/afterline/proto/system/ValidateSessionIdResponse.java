// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: afterline/system.proto

package org.delusion.afterline.proto.system;

/**
 * Protobuf type {@code afterline.ValidateSessionIdResponse}
 */
public final class ValidateSessionIdResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:afterline.ValidateSessionIdResponse)
    ValidateSessionIdResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ValidateSessionIdResponse.newBuilder() to construct.
  private ValidateSessionIdResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ValidateSessionIdResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ValidateSessionIdResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ValidateSessionIdResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 8: {

            valid_ = input.readBool();
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
    return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_ValidateSessionIdResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_ValidateSessionIdResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.delusion.afterline.proto.system.ValidateSessionIdResponse.class, org.delusion.afterline.proto.system.ValidateSessionIdResponse.Builder.class);
  }

  public static final int VALID_FIELD_NUMBER = 1;
  private boolean valid_;
  /**
   * <code>bool valid = 1;</code>
   * @return The valid.
   */
  @java.lang.Override
  public boolean getValid() {
    return valid_;
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
    if (valid_ != false) {
      output.writeBool(1, valid_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (valid_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, valid_);
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
    if (!(obj instanceof org.delusion.afterline.proto.system.ValidateSessionIdResponse)) {
      return super.equals(obj);
    }
    org.delusion.afterline.proto.system.ValidateSessionIdResponse other = (org.delusion.afterline.proto.system.ValidateSessionIdResponse) obj;

    if (getValid()
        != other.getValid()) return false;
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
    hash = (37 * hash) + VALID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getValid());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse parseFrom(
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
  public static Builder newBuilder(org.delusion.afterline.proto.system.ValidateSessionIdResponse prototype) {
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
   * Protobuf type {@code afterline.ValidateSessionIdResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:afterline.ValidateSessionIdResponse)
      org.delusion.afterline.proto.system.ValidateSessionIdResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_ValidateSessionIdResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_ValidateSessionIdResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.delusion.afterline.proto.system.ValidateSessionIdResponse.class, org.delusion.afterline.proto.system.ValidateSessionIdResponse.Builder.class);
    }

    // Construct using org.delusion.afterline.proto.system.ValidateSessionIdResponse.newBuilder()
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
      valid_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.delusion.afterline.proto.system.SystemProtos.internal_static_afterline_ValidateSessionIdResponse_descriptor;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.system.ValidateSessionIdResponse getDefaultInstanceForType() {
      return org.delusion.afterline.proto.system.ValidateSessionIdResponse.getDefaultInstance();
    }

    @java.lang.Override
    public org.delusion.afterline.proto.system.ValidateSessionIdResponse build() {
      org.delusion.afterline.proto.system.ValidateSessionIdResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.system.ValidateSessionIdResponse buildPartial() {
      org.delusion.afterline.proto.system.ValidateSessionIdResponse result = new org.delusion.afterline.proto.system.ValidateSessionIdResponse(this);
      result.valid_ = valid_;
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
      if (other instanceof org.delusion.afterline.proto.system.ValidateSessionIdResponse) {
        return mergeFrom((org.delusion.afterline.proto.system.ValidateSessionIdResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.delusion.afterline.proto.system.ValidateSessionIdResponse other) {
      if (other == org.delusion.afterline.proto.system.ValidateSessionIdResponse.getDefaultInstance()) return this;
      if (other.getValid() != false) {
        setValid(other.getValid());
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
      org.delusion.afterline.proto.system.ValidateSessionIdResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.delusion.afterline.proto.system.ValidateSessionIdResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean valid_ ;
    /**
     * <code>bool valid = 1;</code>
     * @return The valid.
     */
    @java.lang.Override
    public boolean getValid() {
      return valid_;
    }
    /**
     * <code>bool valid = 1;</code>
     * @param value The valid to set.
     * @return This builder for chaining.
     */
    public Builder setValid(boolean value) {
      
      valid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool valid = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearValid() {
      
      valid_ = false;
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


    // @@protoc_insertion_point(builder_scope:afterline.ValidateSessionIdResponse)
  }

  // @@protoc_insertion_point(class_scope:afterline.ValidateSessionIdResponse)
  private static final org.delusion.afterline.proto.system.ValidateSessionIdResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.delusion.afterline.proto.system.ValidateSessionIdResponse();
  }

  public static org.delusion.afterline.proto.system.ValidateSessionIdResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ValidateSessionIdResponse>
      PARSER = new com.google.protobuf.AbstractParser<ValidateSessionIdResponse>() {
    @java.lang.Override
    public ValidateSessionIdResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ValidateSessionIdResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ValidateSessionIdResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ValidateSessionIdResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.delusion.afterline.proto.system.ValidateSessionIdResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

