package app.appmanager.dbHelpers;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.model.requests.RequestData;
import data.model.requests.Requests;
import java.util.List;

public class DbHelperRequest {
  // вся коллекция расписания
  public Requests allList() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    List<RequestData> requests = query.find().toList();
    return new Requests(requests);
  }
  /*
  public ScheduleData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", id);
    ScheduleData schedule = query.find().next();
    return schedule;
  }

  public Schedules findByIdList(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", id);
    List<ScheduleData> schedules = query.find().toList();
    return new Schedules(schedules);
  }

  public Tasks tasksComposition(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> q = datastore.createQuery(TaskData.class).filter("linkUser", id);
    List<TaskData> tasks = q.find().toList();
    return new Tasks(tasks);
  }*/

}
