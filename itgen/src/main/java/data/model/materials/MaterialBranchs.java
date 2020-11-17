package data.model.materials;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MaterialBranchs extends ForwardingSet<MaterialBranchData> {
  private final Set<MaterialBranchData> delegate;

  public MaterialBranchs(MaterialBranchs materialBranchs) {
    this.delegate = new HashSet<MaterialBranchData>(materialBranchs.delegate);
  }

  public MaterialBranchs() { // конструктор без параметров
    this.delegate = new HashSet<MaterialBranchData>();
  }

  public MaterialBranchs(Collection<MaterialBranchData> materialBranchs) {
    this.delegate = new HashSet<MaterialBranchData>(materialBranchs);
  }

  public MaterialBranchs withAdded(MaterialBranchData materialBranch) {
    MaterialBranchs materialBranchs = new MaterialBranchs(this);
    materialBranchs.add(materialBranch);
    return materialBranchs;
  }

  public MaterialBranchs without(MaterialBranchData materialBranch) {
    MaterialBranchs materialBranchs = new MaterialBranchs(this);
    materialBranchs.remove(materialBranch);
    return materialBranchs;
  }

  @Override
  protected Set<MaterialBranchData> delegate() {
    return delegate;
  }
}
