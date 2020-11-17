package data.model.communities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Managers {

  private String id;
  private List<String> roles = new ArrayList<>();

  public Managers() {
  }

  public Managers withId(String id) {
    this.id = id;
    return this;
  }

  public Managers withRoles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public String getId() {
    return id;
  }

  public List<String> getRoles() {
    return roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Managers managers = (Managers) o;
    return Objects.equals(id, managers.id) &&
        Objects.equals(roles, managers.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, roles);
  }

  @Override
  public String toString() {
    return "Managers{" +
        "id='" + id + '\'' +
        ", roles=" + roles +
        '}';
  }
}
