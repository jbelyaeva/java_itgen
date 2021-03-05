package data.services;

import data.dao.MaterialDao;
import data.model.materials.MaterialData;

public class MaterialService {

  private final MaterialDao materialDao = new MaterialDao();

  public MaterialService() {}

  public MaterialData DeleteById(String id) {
    return materialDao.deleteById(id);
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

  public void drop() {
    materialDao.drop();
  }
}
