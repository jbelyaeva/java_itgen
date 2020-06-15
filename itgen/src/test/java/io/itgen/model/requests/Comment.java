package io.itgen.model.requests;

import java.util.Date;
import java.util.Objects;

public class Comment {
  private String id;
  private String owner;
  private String text;
  private Date createAt;

  public Comment() {
  }

  public Comment withId(String id) {
    this.id = id;
    return this;
  }

  public Comment withOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public Comment withText(String text) {
    this.text = text;
    return this;
  }

  public Comment withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getOwner() {
    return owner;
  }

  public String getText() {
    return text;
  }

  public Date getCreateAt() {
    return createAt;
  }

  @Override
  public String toString() {
    return "Comment{" +
            "id='" + id + '\'' +
            ", owner='" + owner + '\'' +
            ", text='" + text + '\'' +
            ", createAt=" + createAt +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id) &&
            Objects.equals(owner, comment.owner) &&
            Objects.equals(text, comment.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, owner, text);
  }
}
