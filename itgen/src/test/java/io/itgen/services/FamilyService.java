package io.itgen.services;

import io.itgen.dao.FamilyDao;
import io.itgen.model.FamilyData;

public class FamilyService {

  private final FamilyDao familyDao = new FamilyDao();

  public FamilyService() {
  }

  public FamilyData DeleteById(String id) {
    return familyDao.findByIdAndDelete(id);
  }

  public void save(FamilyData family) {
    familyDao.save(family);
  }

  public void delete(FamilyData family) {
    familyDao.delete(family);
  }
}
