package data.model.communities;

import java.util.Date;
import java.util.Objects;

public class Subscribers {

  private String id;
  private Date subAt;

  public Subscribers() {
  }

  public Subscribers withId(String id) {
    this.id = id;
    return this;
  }

  public Subscribers withSubAt(Date subAt) {
    this.subAt = subAt;
    return this;
  }

  public String getId() {
    return id;
  }

  public Date getSubAt() {
    return subAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscribers that = (Subscribers) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(subAt, that.subAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subAt);
  }

  @Override
  public String toString() {
    return "Subscribers{" +
        "id='" + id + '\'' +
        ", subAt=" + subAt +
        '}';
  }
}
