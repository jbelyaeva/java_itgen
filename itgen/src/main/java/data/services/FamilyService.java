package data.services;

import data.dao.FamilyDao;
import data.model.family.FamilyData;

public class FamilyService {

  private final FamilyDao familyDao = new FamilyDao();

  public FamilyService() {
  }

  public FamilyData DeleteById(String id) {
    return familyDao.deleteById(id);
  }

  public void save(FamilyData family) {
    familyDao.save(family);
  }

  public void drop() {
    familyDao.drop();
  }

  public void delete(FamilyData family) {
    familyDao.delete(family);
  }

  public void updateField(String idFamily, String nameFiled, Boolean data) {
    familyDao.updateField(idFamily, nameFiled, data);
  }

  public void deleteField(String idFmily, String nameField) {
    familyDao.deleteField(idFmily, nameField);
  }
}
