package data.services;

import data.dao.TestDao;
import data.model.typeform.TestData;

public class TestService {

  private final TestDao testDao = new TestDao();

  public TestService() {}

  public TestData DeleteById(String id) {
    return testDao.deleteById(id);
  }

  public void create(TestData test) {
    testDao.save(test);
  }

  public void save(TestData test) {
    testDao.save(test);
  }

  public TestData findById(String id) {
    return testDao.findById(id);
  }

  public void drop() {
    testDao.drop();
  }

  public void deleteField(String idTest, String nameField) {
    testDao.deleteField(idTest, nameField);
  }

  public void updateField(String idTest, String nameFiled, String data) {
    testDao.updateField(idTest, nameFiled, data);
  }
}
