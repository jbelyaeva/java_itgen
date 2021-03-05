package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.schedule.ScheduleData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class ScheduleDao implements Dao<ScheduleData> {

  @Override
  public ScheduleData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ScheduleData.class).field("id").equal(id).first();
  }

  @Override
  public void save(ScheduleData schedule) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(schedule);
  }

  @Override
  public <E> void updateField(String idSchedule, String nameField, E data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", idSchedule);
    UpdateOperations ops = datastore.createUpdateOperations(ScheduleData.class)
        .set(nameField, data);
    datastore.update(query, (UpdateOperations<ScheduleData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(ScheduleData schedule) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.delete(schedule);
  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public ScheduleData deleteById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", id);
    ScheduleData schedule = datastore.findAndDelete(query);
    return schedule;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class);
    datastore.delete(query);
  }
}
