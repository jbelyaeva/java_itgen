package data.model.materials;

import java.util.ArrayList;
import java.util.List;

public class New {

  private List<String> newMaterials = new ArrayList<>();

  public List<String> getNewMaterials() {
    return newMaterials;
  }

  public New withNewMaterials(List<String> newMaterials) {
    this.newMaterials = newMaterials;
    return this;
  }
}
