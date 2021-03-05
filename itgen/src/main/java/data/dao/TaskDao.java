package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.tasks.TaskData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class TaskDao implements Dao<TaskData> {

  @Override
  public void save(TaskData task) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(task);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("id", id);
    UpdateOperations ops = datastore.createUpdateOperations(TaskData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<TaskData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(TaskData taskData) {

  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class);
    datastore.delete(query);
  }

  @Override
  public TaskData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TaskData.class).field("id").equal(id).first();
  }

  @Override
  public void deleteField(String id, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).field("id").equal(id);
    UpdateOperations ops = datastore.createUpdateOperations(TaskData.class).unset(name);
    datastore.update(query, (UpdateOperations<TaskData>) ops);
  }

  @Override
  public TaskData deleteById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("id", id);
    TaskData task = datastore.findAndDelete(query);
    return task;
  }
}
