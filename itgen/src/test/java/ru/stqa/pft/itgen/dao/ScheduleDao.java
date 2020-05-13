package ru.stqa.pft.itgen.dao;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.WorkerData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class ScheduleDao {

  public ScheduleData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    ScheduleData schedule = entityManager.find( ScheduleData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return schedule;
  }

  public void create( ScheduleData schedule) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(schedule);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void delete( ScheduleData schedule) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(schedule));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}

