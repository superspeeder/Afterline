// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: afterline/asset.proto

package org.delusion.afterline.proto;

/**
 * Protobuf type {@code afterline.AssetStatusResponse}
 */
public final class AssetStatusResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:afterline.AssetStatusResponse)
    AssetStatusResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AssetStatusResponse.newBuilder() to construct.
  private AssetStatusResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AssetStatusResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AssetStatusResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AssetStatusResponse(
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
          case 9: {

            resourceId_ = input.readFixed64();
            break;
          }
          case 16: {

            available_ = input.readBool();
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
    return org.delusion.afterline.proto.AssetProtos.internal_static_afterline_AssetStatusResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.delusion.afterline.proto.AssetProtos.internal_static_afterline_AssetStatusResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.delusion.afterline.proto.AssetStatusResponse.class, org.delusion.afterline.proto.AssetStatusResponse.Builder.class);
  }

  public static final int RESOURCE_ID_FIELD_NUMBER = 1;
  private long resourceId_;
  /**
   * <code>fixed64 resource_id = 1;</code>
   * @return The resourceId.
   */
  @java.lang.Override
  public long getResourceId() {
    return resourceId_;
  }

  public static final int AVAILABLE_FIELD_NUMBER = 2;
  private boolean available_;
  /**
   * <code>bool available = 2;</code>
   * @return The available.
   */
  @java.lang.Override
  public boolean getAvailable() {
    return available_;
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
    if (resourceId_ != 0L) {
      output.writeFixed64(1, resourceId_);
    }
    if (available_ != false) {
      output.writeBool(2, available_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (resourceId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeFixed64Size(1, resourceId_);
    }
    if (available_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, available_);
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
    if (!(obj instanceof org.delusion.afterline.proto.AssetStatusResponse)) {
      return super.equals(obj);
    }
    org.delusion.afterline.proto.AssetStatusResponse other = (org.delusion.afterline.proto.AssetStatusResponse) obj;

    if (getResourceId()
        != other.getResourceId()) return false;
    if (getAvailable()
        != other.getAvailable()) return false;
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
    hash = (37 * hash) + RESOURCE_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getResourceId());
    hash = (37 * hash) + AVAILABLE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getAvailable());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.AssetStatusResponse parseFrom(
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
  public static Builder newBuilder(org.delusion.afterline.proto.AssetStatusResponse prototype) {
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
   * Protobuf type {@code afterline.AssetStatusResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:afterline.AssetStatusResponse)
      org.delusion.afterline.proto.AssetStatusResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.delusion.afterline.proto.AssetProtos.internal_static_afterline_AssetStatusResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.delusion.afterline.proto.AssetProtos.internal_static_afterline_AssetStatusResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.delusion.afterline.proto.AssetStatusResponse.class, org.delusion.afterline.proto.AssetStatusResponse.Builder.class);
    }

    // Construct using org.delusion.afterline.proto.AssetStatusResponse.newBuilder()
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
      resourceId_ = 0L;

      available_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.delusion.afterline.proto.AssetProtos.internal_static_afterline_AssetStatusResponse_descriptor;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.AssetStatusResponse getDefaultInstanceForType() {
      return org.delusion.afterline.proto.AssetStatusResponse.getDefaultInstance();
    }

    @java.lang.Override
    public org.delusion.afterline.proto.AssetStatusResponse build() {
      org.delusion.afterline.proto.AssetStatusResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.AssetStatusResponse buildPartial() {
      org.delusion.afterline.proto.AssetStatusResponse result = new org.delusion.afterline.proto.AssetStatusResponse(this);
      result.resourceId_ = resourceId_;
      result.available_ = available_;
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
      if (other instanceof org.delusion.afterline.proto.AssetStatusResponse) {
        return mergeFrom((org.delusion.afterline.proto.AssetStatusResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.delusion.afterline.proto.AssetStatusResponse other) {
      if (other == org.delusion.afterline.proto.AssetStatusResponse.getDefaultInstance()) return this;
      if (other.getResourceId() != 0L) {
        setResourceId(other.getResourceId());
      }
      if (other.getAvailable() != false) {
        setAvailable(other.getAvailable());
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
      org.delusion.afterline.proto.AssetStatusResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.delusion.afterline.proto.AssetStatusResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long resourceId_ ;
    /**
     * <code>fixed64 resource_id = 1;</code>
     * @return The resourceId.
     */
    @java.lang.Override
    public long getResourceId() {
      return resourceId_;
    }
    /**
     * <code>fixed64 resource_id = 1;</code>
     * @param value The resourceId to set.
     * @return This builder for chaining.
     */
    public Builder setResourceId(long value) {
      
      resourceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>fixed64 resource_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearResourceId() {
      
      resourceId_ = 0L;
      onChanged();
      return this;
    }

    private boolean available_ ;
    /**
     * <code>bool available = 2;</code>
     * @return The available.
     */
    @java.lang.Override
    public boolean getAvailable() {
      return available_;
    }
    /**
     * <code>bool available = 2;</code>
     * @param value The available to set.
     * @return This builder for chaining.
     */
    public Builder setAvailable(boolean value) {
      
      available_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool available = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAvailable() {
      
      available_ = false;
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


    // @@protoc_insertion_point(builder_scope:afterline.AssetStatusResponse)
  }

  // @@protoc_insertion_point(class_scope:afterline.AssetStatusResponse)
  private static final org.delusion.afterline.proto.AssetStatusResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.delusion.afterline.proto.AssetStatusResponse();
  }

  public static org.delusion.afterline.proto.AssetStatusResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AssetStatusResponse>
      PARSER = new com.google.protobuf.AbstractParser<AssetStatusResponse>() {
    @java.lang.Override
    public AssetStatusResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AssetStatusResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AssetStatusResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AssetStatusResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.delusion.afterline.proto.AssetStatusResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

