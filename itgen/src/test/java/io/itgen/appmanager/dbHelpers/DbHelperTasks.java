package io.itgen.appmanager.dbHelpers;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import java.util.List;

public class DbHelperTasks {

  public Tasks tasks() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> q = datastore.createQuery(TaskData.class);
    List<TaskData> tasks = q.find().toList();
    return new Tasks(tasks);
  }

  public TaskData lastTask() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> q = datastore.createQuery(TaskData.class);
    long count = q.count();
    List<TaskData> task = q.find().toList();
    TaskData lastTask = task.get(Math.toIntExact(count - 1));
    return lastTask;
  }
}
