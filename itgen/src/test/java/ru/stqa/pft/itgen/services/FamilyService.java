package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.FamilyDao;
import ru.stqa.pft.itgen.model.FamilyData;

public class FamilyService {
  private FamilyDao familyDao = new FamilyDao();

  public FamilyService() {
  }

  public FamilyData findByIdAndDelete(String id) {
    return familyDao.findByIdAndDelete(id);
  }

  public void save(FamilyData family) {
    familyDao.save(family);
  }

  public void delete(FamilyData family) {
    familyDao.delete(family);
  }
}
