package io.itgen.model.materials;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Objects;

@Entity("materials-new")
public class MaterialNewData {

  @Id
  @Property("_id")
  private String id;

  @Property("trainerId")
  private String trainerId;

  @Embedded("newMaterials")
  private New newMaterials;

  public MaterialNewData withId(String id) {
    this.id = id;
    return this;
  }

  public MaterialNewData withTrainerId(String trainerId) {
    this.trainerId = trainerId;
    return this;
  }

  public MaterialNewData withNewMaterials(New newMaterials) {
    this.newMaterials = newMaterials;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getTrainerId() {
    return trainerId;
  }

  public New getNewMaterials() {
    return newMaterials;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaterialNewData that = (MaterialNewData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(trainerId, that.trainerId)
        && Objects.equals(newMaterials, that.newMaterials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, trainerId, newMaterials);
  }

  @Override
  public String toString() {
    return "MaterialNew{"
        + "id='"
        + id
        + '\''
        + ", trainerId='"
        + trainerId
        + '\''
        + ", newMaterials="
        + newMaterials
        + '}';
  }
}
