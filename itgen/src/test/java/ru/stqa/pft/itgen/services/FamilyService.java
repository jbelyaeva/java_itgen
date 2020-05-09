package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.FamilyDao;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.ParentData;

public class FamilyService {
  private FamilyDao familyDao = new FamilyDao();

  public FamilyService() {
  }

  public void create(FamilyData family) {
    familyDao.create(family);
  }
  public void delete(FamilyData family){
    familyDao.delete(family);
  }
  public void merge(FamilyData family) {
    familyDao.merge(family);
  }
    public FamilyData findById(String id) {
    return familyDao.findById(id);
  }
}
