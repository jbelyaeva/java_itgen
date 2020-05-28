package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.ParentDao;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.ParentData;

public class ParentService {
  private ParentDao parentDao = new ParentDao();

  public ParentService() {
  }

  public ParentData findById(String id) {
    return parentDao.findById(id);
  }

  public void create(ParentData parent) {
    parentDao.save(parent);
  }

  public ParentData findByIdAndDelete(String id) {
    return parentDao.findByIdAndDelete(id);
  }
}
