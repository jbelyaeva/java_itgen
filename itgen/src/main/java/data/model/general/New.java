package data.model.general;

import java.util.Date;
import java.util.Objects;

public class New {

  private Date v;
  private String id;
  private String n;
  private String t;

  public New() {}

  public New withV(Date v) {
    this.v = v;
    return this;
  }

  public New withId(String id) {
    this.id = id;
    return this;
  }

  public New withN(String n) {
    this.n = n;
    return this;
  }

  public New withT(String t) {
    this.t = t;
    return this;
  }

  public Date getV() {
    return v;
  }

  @Override
  public String toString() {
    return "New{" + "v=" + v + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    New aNew = (New) o;
    return Objects.equals(id, aNew.id) && Objects.equals(n, aNew.n);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, n);
  }
}
