package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;
import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class ScheduleDao {

  public Schedules schedules() {
    Datastore datastore = morphiaSessionFactoryUtil();
   /* List<ScheduleData> schedules  = datastore.createQuery(ScheduleData.class)
            .filter("ver", 3).asList();*/
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
    //boolean storeEmpties = datastore.getMapper().getOptions().isStoreEmpties();
    datastore.save(schedule);
  }



  public void delete(ScheduleData schedule) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(schedule));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}

