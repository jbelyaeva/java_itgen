package io.itgen.services;

import io.itgen.dao.ParentDao;
import io.itgen.model.ParentData;

public class ParentService {
  private ParentDao parentDao = new ParentDao();

  public ParentService() {
  }

  public void create(ParentData parent) {
    parentDao.save(parent);
  }

  public ParentData findByIdAndDelete(String id) {
    return parentDao.findByIdAndDelete(id);
  }
}
