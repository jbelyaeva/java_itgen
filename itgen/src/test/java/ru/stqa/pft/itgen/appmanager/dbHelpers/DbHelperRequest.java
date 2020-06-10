package ru.stqa.pft.itgen.appmanager.dbHelpers;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.*;

import java.util.List;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

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
