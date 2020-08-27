package io.itgen.model.materials;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MaterialPerms extends ForwardingSet<MaterialPermsData> {
  private Set<MaterialPermsData> delegate;

  public MaterialPerms(MaterialPerms materialPerms) {
    this.delegate = new HashSet<MaterialPermsData>(materialPerms.delegate);
  }

  public MaterialPerms() { // конструктор без параметров
    this.delegate = new HashSet<MaterialPermsData>();
  }

  public MaterialPerms(Collection<MaterialPermsData> materialPerms) {
    this.delegate = new HashSet<MaterialPermsData>(materialPerms);
  }

  @Override
  protected Set<MaterialPermsData> delegate() {
    return delegate;
  }
}
