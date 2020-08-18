package io.itgen.services;

import io.itgen.dao.MaterialDao;
import io.itgen.model.materials.MaterialData;

public class MaterialService {
  private MaterialDao materialDao = new MaterialDao();

  public MaterialService() {
  }

  public MaterialData findByIdAndDelete(String id) {
    return materialDao.findByIdAndDelete(id);
  }

  public void create(MaterialData material) {
    materialDao.save(material);
  }

  public void save(MaterialData material) {
    materialDao.save(material);
  }

  public MaterialData findById(String id) {
    return materialDao.findById(id);
  }
}
