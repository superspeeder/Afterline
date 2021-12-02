// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: afterline/system.proto

package org.delusion.afterline.proto;

public interface FindUserRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:afterline.FindUserRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>fixed64 session_id = 1;</code>
   * @return The sessionId.
   */
  long getSessionId();

  /**
   * <pre>
   * searches for users with usernames containing the search term (no lexographic similarity checks, but does search case insensitive)
   * </pre>
   *
   * <code>string search_term = 2;</code>
   * @return The searchTerm.
   */
  java.lang.String getSearchTerm();
  /**
   * <pre>
   * searches for users with usernames containing the search term (no lexographic similarity checks, but does search case insensitive)
   * </pre>
   *
   * <code>string search_term = 2;</code>
   * @return The bytes for searchTerm.
   */
  com.google.protobuf.ByteString
      getSearchTermBytes();
}
