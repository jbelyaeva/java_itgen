package io.itgen.model.materials;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MaterialChilds extends ForwardingSet<MaterialChildData> {
  private Set<MaterialChildData> delegate;

  public MaterialChilds(MaterialChilds materialChilds) {
    this.delegate = new HashSet<MaterialChildData>(materialChilds.delegate);
  }

  public MaterialChilds() { // конструктор без параметров
    this.delegate = new HashSet<MaterialChildData>();
  }

  public MaterialChilds(Collection<MaterialChildData> materialChilds) {
    this.delegate = new HashSet<MaterialChildData>(materialChilds);
  }

  public MaterialChilds withAdded(MaterialChildData materialChild) {
    MaterialChilds materialChilds = new MaterialChilds(this);
    materialChilds.add(materialChild);
    return materialChilds;
  }

  public MaterialChilds without(MaterialChildData materialChild) {
    MaterialChilds materialChilds = new MaterialChilds(this);
    materialChilds.remove(materialChild);
    return materialChilds;
  }

  @Override
  protected Set<MaterialChildData> delegate() {
    return delegate;
  }
}
