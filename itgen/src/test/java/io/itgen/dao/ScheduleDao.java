package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.Schedules;
import java.util.List;

public class ScheduleDao {

  public Schedules schedules() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class);
    List<ScheduleData> schedules = query.find().toList();
    return new Schedules(schedules);
  }

  public ScheduleData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", id);
    ScheduleData schedule = datastore.findAndDelete(query);
    return schedule;
  }

  public ScheduleData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ScheduleData.class).field("id").equal(id).first();
  }

  public void save(ScheduleData schedule) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(schedule);
  }

  public void delete(ScheduleData schedule) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.delete(schedule);
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class);
    datastore.delete(query);
  }
}
