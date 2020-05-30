package ru.stqa.pft.itgen.model.tasks;

import dev.morphia.annotations.Property;

import java.util.Objects;

public class D {
   public String neww;

   public D() {
  }

  public D withNeww(String neww) {
    this.neww = neww;
    return this;
  }

  @Override
  public String toString() {
    return "D{" +
            "neww=" + neww +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    D d = (D) o;
    return Objects.equals(neww, d.neww);
  }

  @Override
  public int hashCode() {
    return Objects.hash(neww);
  }
}
