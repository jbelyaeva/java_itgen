package data.services;

import data.dao.MaterialPermsDao;
import data.model.materials.MaterialPermsData;

public class MaterialPermsService {

  private final MaterialPermsDao materialPermsDao = new MaterialPermsDao();

  public MaterialPermsService() {}

  public void save(MaterialPermsData materialPerms) {
    materialPermsDao.save(materialPerms);
  }

  public MaterialPermsData findById(String id) {
    return materialPermsDao.findById(id);
  }
}
