package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.typeform.TestData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class TestDao implements Dao<TestData> {

  @Override
  public void save(TestData test) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(test);
  }

  @Override
  public <T> void updateField(String idTest, String nameField, T data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestData> query = datastore.createQuery(TestData.class).filter("id", idTest);
    UpdateOperations ops = datastore.createUpdateOperations(TestData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<TestData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(TestData testData) {

  }

  @Override
  public TestData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TestData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestData> query = datastore.createQuery(TestData.class);
    datastore.delete(query);
  }

  @Override
  public void deleteField(String id, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestData> query = datastore.createQuery(TestData.class).field("id").equal(id);
    datastore.update(query, datastore.createUpdateOperations(TestData.class).unset(name));
  }

  @Override
  public TestData deleteById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestData> query = datastore.createQuery(TestData.class).filter("id", id);
    return datastore.findAndDelete(query);
  }
}
