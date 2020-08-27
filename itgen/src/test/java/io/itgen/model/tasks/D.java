package io.itgen.model.tasks;

import dev.morphia.annotations.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class D {
  @Property("new")
  public String newData;

  @Property("old")
  public String oldData;

  @Property("langs")
  public List<String> langs = new ArrayList<>();

  public D() {
  }

  public D withNewData(String newData) {
    this.newData = newData;
    return this;
  }

  public D withOldData(String oldData) {
    this.oldData = oldData;
    return this;
  }

  public D withLangs(List<String> langs) {
    this.langs = langs;
    return this;
  }

  @Override
  public String toString() {
    return "D{" +
            "newData=" + newData +
            "oldData=" + oldData +
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
    return Objects.equals(newData, d.newData) &&
        Objects.equals(oldData, d.oldData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newData, oldData);
  }
}
