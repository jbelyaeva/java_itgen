package io.itgen.model.chat;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Date;
import java.util.Objects;

@Entity("chat-subscriptions")
public class ChatSubscriptionData {
  @Id
  @Property("_id")
  private String id;

  @Property("updatedAt")
  private Date updatedAt;

  @Property("type")
  private String type;

  @Property("roomId")
  private String roomId;

  @Property("userId")
  private String userId;

  @Property("msgs")
  private int msgs;

  @Property("alert")
  private boolean alert;

  @Embedded("lastMessage")
  LastMessage lastMessage;

  public ChatSubscriptionData() {}

  public ChatSubscriptionData withId(String id) {
    this.id = id;
    return this;
  }

  public ChatSubscriptionData withUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public ChatSubscriptionData withType(String type) {
    this.type = type;
    return this;
  }

  public ChatSubscriptionData withRoomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  public ChatSubscriptionData withUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public ChatSubscriptionData withMsgs(int msgs) {
    this.msgs = msgs;
    return this;
  }

  public ChatSubscriptionData withAlert(boolean alert) {
    this.alert = alert;
    return this;
  }

  public ChatSubscriptionData withLastMessage(LastMessage lastMessage) {
    this.lastMessage = lastMessage;
    return this;
  }

  public String getId() {
    return id;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public String getType() {
    return type;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getUserId() {
    return userId;
  }

  public int getMsgs() {
    return msgs;
  }

  public boolean getAlert() {
    return alert;
  }

  public LastMessage getLastMessage() {
    return lastMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatSubscriptionData that = (ChatSubscriptionData) o;
    return msgs == that.msgs &&
        alert == that.alert &&
        Objects.equals(id, that.id) &&
        Objects.equals(type, that.type) &&
        Objects.equals(roomId, that.roomId) &&
        Objects.equals(userId, that.userId) &&
        Objects.equals(lastMessage, that.lastMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, roomId, userId, msgs, alert, lastMessage);
  }

  @Override
  public String toString() {
    return "ChatSubscriptionData{" +
        "id='" + id + '\'' +
        ", updatedAt=" + updatedAt +
        ", type='" + type + '\'' +
        ", roomId='" + roomId + '\'' +
        ", userId='" + userId + '\'' +
        ", msgs=" + msgs +
        ", alert=" + alert +
        ", lastMessage=" + lastMessage +
        '}';
  }
}
