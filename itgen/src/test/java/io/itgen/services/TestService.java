package io.itgen.services;

import io.itgen.dao.TestDao;
import io.itgen.model.typeform.TestData;

public class TestService {

  private final TestDao testDao = new TestDao();

  public TestService() {
  }

  public TestData DeleteById(String id) {
    return testDao.findByIdAndDelete(id);
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
}
