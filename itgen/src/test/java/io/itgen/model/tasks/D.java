package io.itgen.model.tasks;

import dev.morphia.annotations.Property;

import java.util.Objects;

public class D {
  @Property("new")
  public String neww;

  @Property("old")
  public String old;
  public D() {
  }

  public D withNeww(String neww) {
    this.neww = neww;
    return this;
  }

  public D withOld(String old) {
    this.old = old;
    return this;
  }

  @Override
  public String toString() {
    return "D{" +
            "neww=" + neww +
            "old=" + old +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    D d = (D) o;
    return Objects.equals(neww, d.neww) &&
        Objects.equals(old, d.old);
  }

  @Override
  public int hashCode() {
    return Objects.hash(neww, old);
  }
}
