package data.services;

import data.dao.ParentDao;
import data.model.users.ParentData;

public class ParentService {

  private final ParentDao parentDao = new ParentDao();

  public ParentService() {}

  public void save(ParentData parent) {
    parentDao.save(parent);
  }

  public ParentData DeleteById(String id) {
    return parentDao.findByIdAndDelete(id);
  }

  public ParentData findById(String id) {
    return parentDao.findById(id);
  }

  public void deleteField(String idParent, String nameField) {
    parentDao.deleteField(idParent, nameField);
  }
}
