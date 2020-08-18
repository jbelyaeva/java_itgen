package io.itgen.model.materials;

import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Branches {
  private String id;
  private String name;
  private List<String> materialsOrder = new ArrayList<>();

  public Branches() {
  }


  public Branches withId(String id) {
    this.id = id;
    return this;
  }

  public Branches withName(String name) {
    this.name = name;
    return this;
  }

  public Branches withMaterialsOrder(List<String> materialsOrder) {
    this.materialsOrder = materialsOrder;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<String> getMaterialsOrder() {
    return materialsOrder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Branches branches = (Branches) o;
    return Objects.equals(id, branches.id) &&
        Objects.equals(name, branches.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "Branches{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", materialsOrder=" + materialsOrder +
        '}';
  }
}
