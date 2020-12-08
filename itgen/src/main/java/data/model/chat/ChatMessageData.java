package data.model.chat;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Date;
import java.util.Objects;

@Entity("chat-messages")
public class ChatMessageData {

  @Id
  @Property("_id")
  private String id;

  @Property("ts")
  private Date ts;

  @Property("rId")
  private String rId;

  @Property("text")
  private String text;

  @Embedded
  private User user;

  public ChatMessageData() {
  }

  public ChatMessageData withId(String id) {
    this.id = id;
    return this;
  }

  public ChatMessageData withTs(Date ts) {
    this.ts = ts;
    return this;
  }

  public ChatMessageData withrId(String rId) {
    this.rId = rId;
    return this;
  }

  public ChatMessageData withText(String text) {
    this.text = text;
    return this;
  }

  public ChatMessageData withUser(User user) {
    this.user = user;
    return this;
  }

  public String getId() {
    return id;
  }

  public Date getTs() {
    return ts;
  }

  public String getrId() {
    return rId;
  }

  public String getText() {
    return text;
  }

  public User getUser() {
    return user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatMessageData that = (ChatMessageData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(rId, that.rId)
        && Objects.equals(text, that.text)
        && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rId, text, user);
  }

  @Override
  public String toString() {
    return "ChatMessageData{" +
        "id='" + id + '\'' +
        ", ts=" + ts +
        ", rId='" + rId + '\'' +
        ", text='" + text + '\'' +
        ", user=" + user +
        '}';
  }
}
