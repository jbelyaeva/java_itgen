package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import io.itgen.model.LeadData;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.StudentData;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class TaskDao {

  public void save(TaskData task) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(task);
  }

  public TaskData findByIdAndDeleteTask(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("id", id);
    TaskData task = datastore.findAndDelete(query);
    return task;
  }

  public TaskData findByIdAndDeleteTask(StudentData student) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("linkUser", student.getId());
    TaskData task = datastore.findAndDelete(query);
    return task;
  }
  public TaskData findByIdAndDeleteTask(LeadData lead) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("linkLead", lead.getId());
    TaskData task = datastore.findAndDelete(query);
    return task;
  }

  public void drop () {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class);
    datastore.delete(query);
  }

  public TaskData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TaskData.class).field("id").equal(id).first();
  }

  public void deleteField(String id, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).field("id").equal(id);
    UpdateOperations ops = datastore.createUpdateOperations(TaskData.class).unset(name);
    datastore.update(query, (UpdateOperations<TaskData>) ops);
  }


}

