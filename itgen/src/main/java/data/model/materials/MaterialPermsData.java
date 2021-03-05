package data.model.materials;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("materials-perms")
public class MaterialPermsData {

  @Id
  @Property("_id")
  private String id;

  @Property("className")
  private String className;

  @Property("perms")
  private List<String> perms = new ArrayList<>();

  @Property("reviewPerms")
  private List<String> reviewPerms = new ArrayList<>();

  public MaterialPermsData withId(String id) {
    this.id = id;
    return this;
  }

  public MaterialPermsData withPerms(List<String> perms) {
    this.perms = perms;
    return this;
  }

  public MaterialPermsData withReviewPerms(List<String> reviewPerms) {
    this.reviewPerms = reviewPerms;
    return this;
  }

  public String getId() {
    return id;
  }

  public List<String> getPerms() {
    return perms;
  }

  public List<String> getReviewPerms() {
    return reviewPerms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaterialPermsData that = (MaterialPermsData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(perms, that.perms)
        && Objects.equals(reviewPerms, that.reviewPerms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, perms, reviewPerms);
  }

  @Override
  public String toString() {
    return "MaterialPermsData{"
        + "id='"
        + id
        + '\''
        + ", perms="
        + perms
        + ", reviewPerms="
        + reviewPerms
        + '}';
  }
}
