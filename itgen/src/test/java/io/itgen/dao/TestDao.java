package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.typeform.TestData;

public class TestDao {

  public void save(TestData test) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(test);
  }

  public TestData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestData> query = datastore.createQuery(TestData.class).filter("id", id);
    return datastore.findAndDelete(query);
  }

  public TestData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TestData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestData> query = datastore.createQuery(TestData.class);
    datastore.delete(query);
  }

  public void deleteField(String id, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TestData> query = datastore.createQuery(TestData.class).field("id").equal(id);
    datastore.update(query, datastore.createUpdateOperations(TestData.class).unset(name));
  }
}
