// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: afterline/system.proto

package org.delusion.afterline.proto.system;

public interface GetUserStatusRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:afterline.GetUserStatusRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>fixed64 session_id = 1;</code>
   * @return The sessionId.
   */
  long getSessionId();

  /**
   * <code>fixed64 user_id = 2;</code>
   * @return The userId.
   */
  long getUserId();

  /**
   * <code>bool available = 3;</code>
   * @return The available.
   */
  boolean getAvailable();
}
