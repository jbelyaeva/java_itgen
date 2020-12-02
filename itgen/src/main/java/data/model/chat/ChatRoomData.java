package data.model.chat;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("chat-rooms")
public class ChatRoomData {

  @Id
  @Property("_id")
  private String id;

  @Property("createdAt")
  private Date createdAt;

  @Property("type")
  private String type;

  @Property("ownerId")
  private String ownerId;

  @Embedded("users")
  private List<Users> users = new ArrayList<>();

  public ChatRoomData() {
  }

  public ChatRoomData withId(String id) {
    this.id = id;
    return this;
  }

  public ChatRoomData withCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public ChatRoomData withType(String type) {
    this.type = type;
    return this;
  }

  public ChatRoomData withOwnerId(String ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  public ChatRoomData withUsers(List<Users> users) {
    this.users = users;
    return this;
  }

  public String getId() {
    return id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getType() {
    return type;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public List<Users> getUsers() {
    return users;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatRoomData that = (ChatRoomData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(type, that.type)
        && Objects.equals(ownerId, that.ownerId)
        && Objects.equals(users, that.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, ownerId, users);
  }

  @Override
  public String toString() {
    return "ChatRoomData{" +
        "id='" + id + '\'' +
        ", createdAt=" + createdAt +
        ", type='" + type + '\'' +
        ", ownerId='" + ownerId + '\'' +
        ", users=" + users +
        '}';
  }
}
