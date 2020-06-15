package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.LeadData;
import io.itgen.model.TaskData;
import io.itgen.model.StudentData;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class TaskDao {

  public void save(TaskData task) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(task);
  }

  public TaskData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("id", id);
    TaskData task = datastore.findAndDelete(query);
    return task;
  }

  public TaskData findByIdAndDelete(StudentData student) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("linkUser", student.getId());
    TaskData task = datastore.findAndDelete(query);
    return task;
  }
  public TaskData findByIdAndDeleteLead (LeadData lead) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> query = datastore.createQuery(TaskData.class).filter("linkUser", lead.getId());
    TaskData task = datastore.findAndDelete(query);
    return task;
  }

}
