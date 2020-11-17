package data.model.materials;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Materials extends ForwardingSet<MaterialData> {
  private final Set<MaterialData> delegate;

  public Materials(Materials materials) {
    this.delegate = new HashSet<MaterialData>(materials.delegate);
  }

  public Materials() { // конструктор без параметров
    this.delegate = new HashSet<MaterialData>();
  }

  public Materials(Collection<MaterialData> materials) {
    this.delegate = new HashSet<MaterialData>(materials);
  }

  public Materials withAdded(MaterialData material) {
    Materials materials = new Materials(this);
    materials.add(material);
    return materials;
  }

  public MaterialData withCreatorId(String id) {
    Materials materials = new Materials(this);
    MaterialData material = materials.iterator().next().withCreator(id);
    return material;
  }

  public Materials without(MaterialData material) {
    Materials materials = new Materials(this);
    materials.remove(material);
    return materials;
  }

  @Override
  protected Set<MaterialData> delegate() {
    return delegate;
  }
}
