package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import data.model.typeform.TestResultsData;

public class TestResultDao {

  public void save(TestResultsData testResult) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(testResult);
  }

  public TestResultsData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TestResultsData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestResultsData> query = datastore.createQuery(TestResultsData.class);
    datastore.delete(query);
  }

  public void deleteField(String id, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestResultsData> query =
        datastore.createQuery(TestResultsData.class).field("id").equal(id);
    datastore.update(query, datastore.createUpdateOperations(TestResultsData.class).unset(name));
  }

  public void updateField(String idTestResult, String nameFiled, String data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestResultsData> query = datastore.createQuery(TestResultsData.class).filter("id", idTestResult);
    UpdateOperations ops = datastore.createUpdateOperations(TestResultsData.class).set(nameFiled, data);
    datastore.update(query, (UpdateOperations<TestResultsData>) ops);
  }
}
