package data.services;

import data.dao.MaterialNewDao;
import data.model.materials.MaterialNewData;

public class MaterialNewService {

  private final MaterialNewDao materialNewDao = new MaterialNewDao();

  public MaterialNewService() {
  }

  public void save(MaterialNewData materialNew) {
    materialNewDao.save(materialNew);
  }

  public void drop() {
    materialNewDao.drop();
  }

  public MaterialNewData findById(String id) {
    return materialNewDao.findById(id);
  }
}
