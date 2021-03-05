package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.typeform.TestResultsData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class TestResultDao implements Dao<TestResultsData> {

  @Override
  public void save(TestResultsData testResult) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(testResult);
  }

  @Override
  public <T> void updateField(String idTestResult, String nameField, T data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestResultsData> query = datastore.createQuery(TestResultsData.class)
        .filter("id", idTestResult);
    UpdateOperations ops = datastore.createUpdateOperations(TestResultsData.class)
        .set(nameField, data);
    datastore.update(query, (UpdateOperations<TestResultsData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(TestResultsData testResultsData) {

  }

  @Override
  public TestResultsData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TestResultsData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestResultsData> query = datastore.createQuery(TestResultsData.class);
    datastore.delete(query);
  }

  @Override
  public void deleteField(String id, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestResultsData> query =
        datastore.createQuery(TestResultsData.class).field("id").equal(id);
    datastore.update(query, datastore.createUpdateOperations(TestResultsData.class).unset(name));
  }

  @Override
  public TestResultsData deleteById(String id) {
    return null;
  }
}
