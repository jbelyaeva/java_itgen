package io.itgen.model.general;

import java.util.Date;
import java.util.Objects;

public class Comments {
  private String id;
  private String owner;
  private String text;
  private Date createAt;

  public Comments() {}

  public Comments withId(String id) {
    this.id = id;
    return this;
  }

  public Comments withOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public Comments withText(String text) {
    this.text = text;
    return this;
  }

  public Comments withCreateAt(Date createAt) {
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
    return "Comments{"
        + "id='"
        + id
        + '\''
        + ", owner='"
        + owner
        + '\''
        + ", text='"
        + text
        + '\''
        + ", createAt="
        + createAt
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comments comments = (Comments) o;
    return Objects.equals(owner, comments.owner) && Objects.equals(text, comments.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(owner, text);
  }
}
