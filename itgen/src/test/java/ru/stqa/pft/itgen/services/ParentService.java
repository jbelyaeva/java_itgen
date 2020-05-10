package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.ParentDao;
import ru.stqa.pft.itgen.model.ParentData;

public class ParentService {
  private ParentDao parentDao = new ParentDao();

  public ParentService() {
  }

  public ParentData findById(String id) {
    return parentDao.findById(id);
  }

  public void create(ParentData parent) {
    parentDao.create(parent);
  }

  public void delete(ParentData parent) {
    parentDao.delete(parent);
  }
}
