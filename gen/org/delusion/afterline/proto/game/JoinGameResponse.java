// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: afterline/game.proto

package org.delusion.afterline.proto.game;

/**
 * Protobuf type {@code afterline.JoinGameResponse}
 */
public final class JoinGameResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:afterline.JoinGameResponse)
    JoinGameResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use JoinGameResponse.newBuilder() to construct.
  private JoinGameResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private JoinGameResponse() {
    status_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new JoinGameResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private JoinGameResponse(
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

            gameId_ = input.readFixed64();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            status_ = rawValue;
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
    return org.delusion.afterline.proto.game.GameProtos.internal_static_afterline_JoinGameResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.delusion.afterline.proto.game.GameProtos.internal_static_afterline_JoinGameResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.delusion.afterline.proto.game.JoinGameResponse.class, org.delusion.afterline.proto.game.JoinGameResponse.Builder.class);
  }

  /**
   * Protobuf enum {@code afterline.JoinGameResponse.Status}
   */
  public enum Status
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>DISALLOWED = 0;</code>
     */
    DISALLOWED(0),
    /**
     * <code>PENDING = 1;</code>
     */
    PENDING(1),
    /**
     * <code>JOINED = 2;</code>
     */
    JOINED(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>DISALLOWED = 0;</code>
     */
    public static final int DISALLOWED_VALUE = 0;
    /**
     * <code>PENDING = 1;</code>
     */
    public static final int PENDING_VALUE = 1;
    /**
     * <code>JOINED = 2;</code>
     */
    public static final int JOINED_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Status valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static Status forNumber(int value) {
      switch (value) {
        case 0: return DISALLOWED;
        case 1: return PENDING;
        case 2: return JOINED;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Status>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Status> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Status>() {
            public Status findValueByNumber(int number) {
              return Status.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.delusion.afterline.proto.game.JoinGameResponse.getDescriptor().getEnumTypes().get(0);
    }

    private static final Status[] VALUES = values();

    public static Status valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Status(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:afterline.JoinGameResponse.Status)
  }

  public static final int GAME_ID_FIELD_NUMBER = 1;
  private long gameId_;
  /**
   * <code>fixed64 game_id = 1;</code>
   * @return The gameId.
   */
  @java.lang.Override
  public long getGameId() {
    return gameId_;
  }

  public static final int STATUS_FIELD_NUMBER = 2;
  private int status_;
  /**
   * <code>.afterline.JoinGameResponse.Status status = 2;</code>
   * @return The enum numeric value on the wire for status.
   */
  @java.lang.Override public int getStatusValue() {
    return status_;
  }
  /**
   * <code>.afterline.JoinGameResponse.Status status = 2;</code>
   * @return The status.
   */
  @java.lang.Override public org.delusion.afterline.proto.game.JoinGameResponse.Status getStatus() {
    @SuppressWarnings("deprecation")
    org.delusion.afterline.proto.game.JoinGameResponse.Status result = org.delusion.afterline.proto.game.JoinGameResponse.Status.valueOf(status_);
    return result == null ? org.delusion.afterline.proto.game.JoinGameResponse.Status.UNRECOGNIZED : result;
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
    if (gameId_ != 0L) {
      output.writeFixed64(1, gameId_);
    }
    if (status_ != org.delusion.afterline.proto.game.JoinGameResponse.Status.DISALLOWED.getNumber()) {
      output.writeEnum(2, status_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (gameId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeFixed64Size(1, gameId_);
    }
    if (status_ != org.delusion.afterline.proto.game.JoinGameResponse.Status.DISALLOWED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, status_);
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
    if (!(obj instanceof org.delusion.afterline.proto.game.JoinGameResponse)) {
      return super.equals(obj);
    }
    org.delusion.afterline.proto.game.JoinGameResponse other = (org.delusion.afterline.proto.game.JoinGameResponse) obj;

    if (getGameId()
        != other.getGameId()) return false;
    if (status_ != other.status_) return false;
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
    hash = (37 * hash) + GAME_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getGameId());
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.delusion.afterline.proto.game.JoinGameResponse parseFrom(
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
  public static Builder newBuilder(org.delusion.afterline.proto.game.JoinGameResponse prototype) {
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
   * Protobuf type {@code afterline.JoinGameResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:afterline.JoinGameResponse)
      org.delusion.afterline.proto.game.JoinGameResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.delusion.afterline.proto.game.GameProtos.internal_static_afterline_JoinGameResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.delusion.afterline.proto.game.GameProtos.internal_static_afterline_JoinGameResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.delusion.afterline.proto.game.JoinGameResponse.class, org.delusion.afterline.proto.game.JoinGameResponse.Builder.class);
    }

    // Construct using org.delusion.afterline.proto.game.JoinGameResponse.newBuilder()
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
      gameId_ = 0L;

      status_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.delusion.afterline.proto.game.GameProtos.internal_static_afterline_JoinGameResponse_descriptor;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.game.JoinGameResponse getDefaultInstanceForType() {
      return org.delusion.afterline.proto.game.JoinGameResponse.getDefaultInstance();
    }

    @java.lang.Override
    public org.delusion.afterline.proto.game.JoinGameResponse build() {
      org.delusion.afterline.proto.game.JoinGameResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.delusion.afterline.proto.game.JoinGameResponse buildPartial() {
      org.delusion.afterline.proto.game.JoinGameResponse result = new org.delusion.afterline.proto.game.JoinGameResponse(this);
      result.gameId_ = gameId_;
      result.status_ = status_;
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
      if (other instanceof org.delusion.afterline.proto.game.JoinGameResponse) {
        return mergeFrom((org.delusion.afterline.proto.game.JoinGameResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.delusion.afterline.proto.game.JoinGameResponse other) {
      if (other == org.delusion.afterline.proto.game.JoinGameResponse.getDefaultInstance()) return this;
      if (other.getGameId() != 0L) {
        setGameId(other.getGameId());
      }
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
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
      org.delusion.afterline.proto.game.JoinGameResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.delusion.afterline.proto.game.JoinGameResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long gameId_ ;
    /**
     * <code>fixed64 game_id = 1;</code>
     * @return The gameId.
     */
    @java.lang.Override
    public long getGameId() {
      return gameId_;
    }
    /**
     * <code>fixed64 game_id = 1;</code>
     * @param value The gameId to set.
     * @return This builder for chaining.
     */
    public Builder setGameId(long value) {
      
      gameId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>fixed64 game_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearGameId() {
      
      gameId_ = 0L;
      onChanged();
      return this;
    }

    private int status_ = 0;
    /**
     * <code>.afterline.JoinGameResponse.Status status = 2;</code>
     * @return The enum numeric value on the wire for status.
     */
    @java.lang.Override public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.afterline.JoinGameResponse.Status status = 2;</code>
     * @param value The enum numeric value on the wire for status to set.
     * @return This builder for chaining.
     */
    public Builder setStatusValue(int value) {
      
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.afterline.JoinGameResponse.Status status = 2;</code>
     * @return The status.
     */
    @java.lang.Override
    public org.delusion.afterline.proto.game.JoinGameResponse.Status getStatus() {
      @SuppressWarnings("deprecation")
      org.delusion.afterline.proto.game.JoinGameResponse.Status result = org.delusion.afterline.proto.game.JoinGameResponse.Status.valueOf(status_);
      return result == null ? org.delusion.afterline.proto.game.JoinGameResponse.Status.UNRECOGNIZED : result;
    }
    /**
     * <code>.afterline.JoinGameResponse.Status status = 2;</code>
     * @param value The status to set.
     * @return This builder for chaining.
     */
    public Builder setStatus(org.delusion.afterline.proto.game.JoinGameResponse.Status value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.afterline.JoinGameResponse.Status status = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatus() {
      
      status_ = 0;
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


    // @@protoc_insertion_point(builder_scope:afterline.JoinGameResponse)
  }

  // @@protoc_insertion_point(class_scope:afterline.JoinGameResponse)
  private static final org.delusion.afterline.proto.game.JoinGameResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.delusion.afterline.proto.game.JoinGameResponse();
  }

  public static org.delusion.afterline.proto.game.JoinGameResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<JoinGameResponse>
      PARSER = new com.google.protobuf.AbstractParser<JoinGameResponse>() {
    @java.lang.Override
    public JoinGameResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new JoinGameResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<JoinGameResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<JoinGameResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.delusion.afterline.proto.game.JoinGameResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

