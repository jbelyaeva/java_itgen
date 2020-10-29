package io.itgen.services;

import io.itgen.dao.MaterialNewDao;
import io.itgen.model.materials.MaterialNewData;

public class MaterialNewService {

  private final MaterialNewDao materialNewDao = new MaterialNewDao();

  public MaterialNewService() {}

  public void save(MaterialNewData materialNew) {
    materialNewDao.save(materialNew);
  }

  public void drop() {
    materialNewDao.drop();
  }
}
