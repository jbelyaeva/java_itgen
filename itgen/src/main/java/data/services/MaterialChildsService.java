package data.services;

import data.dao.MaterialChildDao;
import data.model.materials.MaterialChildData;

public class MaterialChildsService {

  private final MaterialChildDao materialChildDao = new MaterialChildDao();

  public MaterialChildsService() {}

  public void save(MaterialChildData materialChild) {
    materialChildDao.save(materialChild);
  }

  public MaterialChildData findById(String id) {
    return materialChildDao.findById(id);
  }

  public void drop() {
    materialChildDao.drop();
  }
}
