package data.model.general;

import java.util.Date;
import java.util.Objects;

public class Old {

  private Date v;
  private String id;
  private String n;
  private String t;

  public Old() {}

  public Old withV(Date v) {
    this.v = v;
    return this;
  }

  public Old withId(String id) {
    this.id = id;
    return this;
  }

  public Old withN(String n) {
    this.n = n;
    return this;
  }

  public Old withT(String t) {
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
    Old old = (Old) o;
    return Objects.equals(id, old.id) && Objects.equals(n, old.n);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, n);
  }
}
