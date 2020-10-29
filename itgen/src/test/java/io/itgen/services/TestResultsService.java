package io.itgen.services;

import io.itgen.dao.TestResultDao;
import io.itgen.model.typeform.TestResultsData;

public class TestResultsService {

  private final TestResultDao testResultDao = new TestResultDao();

  public TestResultsService() {}

  public void create(TestResultsData testResult) {
    testResultDao.save(testResult);
  }

  public void save(TestResultsData test) {
    testResultDao.save(test);
  }

  public TestResultsData findById(String id) {
    return testResultDao.findById(id);
  }

  public void drop() {
    testResultDao.drop();
  }

  public void deleteField(String idTest, String nameField) {
    testResultDao.deleteField(idTest, nameField);
  }
}
