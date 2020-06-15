package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.ScheduleData;
import io.itgen.model.Schedules;

import java.util.List;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class ScheduleDao {

  public Schedules schedules() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class);
    List<ScheduleData> schedules = query.find().toList();
    return new Schedules(schedules);
  }

  public ScheduleData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class)
            .filter("id", id);
    ScheduleData schedule = datastore.findAndDelete(query);
    return schedule;
  }

  public void save(ScheduleData schedule) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(schedule);
  }

  public void delete(ScheduleData schedule) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.delete(schedule);
  }

}

