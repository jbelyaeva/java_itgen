package io.itgen.model.general;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class D {
  @Property("new")
  public String newData;

  @Property("old")
  public String oldData;

  @Embedded
  public New newChangeData;

  @Embedded
  public Old oldChangeData;

  @Property("langs")
  public List<String> langs = new ArrayList<>();

  @Property("from")
  public Boolean from;

  @Property("to")
  public Boolean to;

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

  public D withFrom(Boolean from) {
    this.from = from;
    return this;
  }

  public D withTo(Boolean to) {
    this.to = to;
    return this;
  }

  public D withNewChangeData(New newChangeData) {
    this.newChangeData = newChangeData;
    return this;
  }

  public D withOldChangeData(Old oldChangeData) {
    this.oldChangeData = oldChangeData;
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
