package data.services;

import data.dao.FamilyDao;
import data.model.family.FamilyData;

public class FamilyService {

  private final FamilyDao familyDao = new FamilyDao();

  public FamilyService() {}

  public FamilyData DeleteById(String id) {
    return familyDao.findByIdAndDelete(id);
  }

  public void save(FamilyData family) {
    familyDao.save(family);
  }

  public void delete(FamilyData family) {
    familyDao.delete(family);
  }

  public void updateFieldBoolean(String idFamily, String nameFiled, Boolean data) {
    familyDao.updateFieldBoolean(idFamily, nameFiled, data);
  }
}
