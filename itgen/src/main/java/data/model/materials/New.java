package data.model.materials;

import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.List;

public class New {

  @Property("1")
  private List<String> newMaterials = new ArrayList<>();

  public List<String> getNewMaterials() {
    return newMaterials;
  }

  public New withNewMaterials(List<String> newMaterials) {
    this.newMaterials = newMaterials;
    return this;
  }
}
