package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.FamilyDao;
import ru.stqa.pft.itgen.model.FamilyData;

public class FamilyService {
  private FamilyDao familyDao = new FamilyDao();

  public FamilyService() {
  }

  public FamilyData findById(String id) {
    return familyDao.findById(id);
  }

  public void create(FamilyData family) {
    familyDao.create(family);
  }

  public void delete(FamilyData family) {
    familyDao.delete(family);
  }
}
