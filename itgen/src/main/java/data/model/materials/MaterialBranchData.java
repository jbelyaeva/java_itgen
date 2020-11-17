package data.model.materials;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("materials-branches")
public class MaterialBranchData {
  @Id
  @Property("_id")
  private String id;

  @Embedded("branches")
  private List<Branches> branches = new ArrayList<Branches>();

  public MaterialBranchData withId(String id) {
    this.id = id;
    return this;
  }

  public MaterialBranchData withBranches(List<Branches> branches) {
    this.branches = branches;
    return this;
  }

  public String getId() {
    return id;
  }

  public List<Branches> getBranches() {
    return branches;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaterialBranchData that = (MaterialBranchData) o;
    return Objects.equals(id, that.id) && Objects.equals(branches, that.branches);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, branches);
  }

  @Override
  public String toString() {
    return "MaterialBranchData{" + "id='" + id + '\'' + ", branches=" + branches + '}';
  }
}
