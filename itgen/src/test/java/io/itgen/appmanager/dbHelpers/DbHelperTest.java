package io.itgen.appmanager.dbHelpers;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.typeform.TestData;
import io.itgen.model.typeform.Tests;
import java.util.List;

public class DbHelperTest {

  Datastore datastore = morphiaSessionFactoryUtil();

  public Tests tests() {
    Query<TestData> q = datastore.createQuery(TestData.class);
    List<TestData> tests = q.find().toList();
    return new Tests(tests);
  }

  public TestData lastTest() {
    Query<TestData> q = datastore.createQuery(TestData.class);
    long count = q.count();
    List<TestData> task = q.find().toList();
    TestData lastTest = task.get(Math.toIntExact(count - 1));
    return lastTest;
  }

  public Tests waitAndGetNewDataFromBD(Tests before) {
    List<TestData> tests = null;
    for (int i = 0; i < 5; i++) {
      Query<TestData> q = datastore.createQuery(TestData.class);
      tests = q.find().toList();
      if (tests.size() == before.size() + 1) {
        break;
      }
    }
    return new Tests(tests);
  }
}
