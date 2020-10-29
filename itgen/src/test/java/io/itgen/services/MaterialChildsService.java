package io.itgen.services;

import io.itgen.dao.MaterialChildDao;
import io.itgen.model.materials.MaterialChildData;

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
