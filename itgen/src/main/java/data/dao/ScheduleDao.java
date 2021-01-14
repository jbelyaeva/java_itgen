package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
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

  public <E> void updateField(String idSchedule, String nameField, E data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", idSchedule);
    UpdateOperations ops = datastore.createUpdateOperations(ScheduleData.class)
        .set(nameField, data);
    datastore.update(query, (UpdateOperations<ScheduleData>) ops);
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
