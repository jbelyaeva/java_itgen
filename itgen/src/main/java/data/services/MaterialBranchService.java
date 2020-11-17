package data.services;

import data.dao.MaterialBranchDao;
import data.model.materials.MaterialBranchData;

public class MaterialBranchService {

  private final MaterialBranchDao materialBranchDao = new MaterialBranchDao();

  public MaterialBranchService() {}

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

  public void drop() {
    materialBranchDao.drop();
  }
}
