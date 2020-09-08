package io.itgen.services;

import io.itgen.dao.MaterialBranchDao;
import io.itgen.model.materials.MaterialBranchData;

public class MaterialBranchService {

  private final MaterialBranchDao materialBranchDao = new MaterialBranchDao();

  public MaterialBranchService() {
  }

  public MaterialBranchData DeleteById(String id) {
    return materialBranchDao.findByIdAndDelete(id);
  }

  public void create(MaterialBranchData materialBranch) {
    materialBranchDao.save(materialBranch);
  }

  public void save(MaterialBranchData materialBranch) {
    materialBranchDao.save(materialBranch);
  }

  public MaterialBranchData findById(String id) {
    return materialBranchDao.findById(id);
  }
}
