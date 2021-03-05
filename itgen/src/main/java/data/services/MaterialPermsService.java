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

  public <E> void updateField(String idPerms, String nameFiled, E[] data) {
    materialPermsDao.updateField(idPerms, nameFiled, data);
  }

  public <E> void updateFieldClass(String idPerms, String nameFiled, E data) {
    materialPermsDao.updateField(idPerms, nameFiled, data);
  }

  public void deleteField(String idPerms, String nameField) {
    materialPermsDao.deleteField(idPerms, nameField);
  }

  public void delete(MaterialPermsData perms) {
    materialPermsDao.delete(perms);
  }
}
