package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.users.WorkerData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class WorkerDao implements Dao<WorkerData> {

  @Override
  public void save(WorkerData worker) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(worker);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(WorkerData workerData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public WorkerData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<WorkerData> query = datastore.createQuery(WorkerData.class).filter("id", id);
    WorkerData worker = datastore.findAndDelete(query);
    return worker;
  }

  @Override
  public WorkerData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(WorkerData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {

  }
}
