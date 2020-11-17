package data.model.materials;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import data.model.general.Activity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("materials-childs")
public class MaterialChildData {

  @Id
  @Property("_id")
  private String id;

  @Property("childId")
  private String childId;

  @Property("materialId")
  private String materialId;

  @Property("hw")
  Boolean hw;

  @Property("status")
  String status;

  @Embedded("activity")
  private List<Activity> activity = new ArrayList<>();

  public MaterialChildData() {}

  public MaterialChildData withId(String id) {
    this.id = id;
    return this;
  }

  public MaterialChildData withChildId(String childId) {
    this.childId = childId;
    return this;
  }

  public MaterialChildData withMaterialId(String materialId) {
    this.materialId = materialId;
    return this;
  }

  public MaterialChildData withHw (Boolean hw) {
    this.hw = hw;
    return this;
  }

  public MaterialChildData withStatus (String status) {
    this.status = status;
    return this;
  }

  public MaterialChildData withActivity(List<Activity> activity) {
    this.activity = activity;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getChildId() {
    return childId;
  }

  public String getMaterialId() {
    return materialId;
  }

  public List<Activity> getActivity() {
    return activity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaterialChildData that = (MaterialChildData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(childId, that.childId)
        && Objects.equals(materialId, that.materialId)
        && Objects.equals(activity, that.activity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, childId, materialId, activity);
  }

  @Override
  public String toString() {
    return "MaterialChildData{"
        + "id='"
        + id
        + '\''
        + ", childId='"
        + childId
        + '\''
        + ", materialId='"
        + materialId
        + '\''
        + ", activity="
        + activity
        + '}';
  }
}
