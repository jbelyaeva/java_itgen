package io.itgen.model.chat;

import java.util.Date;
import java.util.Objects;

public class LastMessage {

  private String id;
  private Date ts;
  private String rId;
  private String text;
  private User user;

  public LastMessage() {
  }

  public LastMessage withId(String id) {
    this.id = id;
    return this;
  }

  public LastMessage withTs(Date ts) {
    this.ts = ts;
    return this;
  }

  public LastMessage withrId(String rId) {
    this.rId = rId;
    return this;
  }

  public LastMessage withText(String text) {
    this.text = text;
    return this;
  }

  public LastMessage withUser(User user) {
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
    LastMessage that = (LastMessage) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(rId, that.rId) &&
        Objects.equals(text, that.text) &&
        Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rId, text, user);
  }

  @Override
  public String toString() {
    return "LastMessage{" +
        "id='" + id + '\'' +
        ", ts=" + ts +
        ", rId='" + rId + '\'' +
        ", text='" + text + '\'' +
        ", user=" + user +
        '}';
  }
}
