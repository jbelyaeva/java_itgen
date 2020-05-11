package ru.stqa.pft.itgen.dao;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.WorkerData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class WorkerDao {

  public WorkerData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    WorkerData worker = entityManager.find( WorkerData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return worker;
  }

  public void create( WorkerData worker) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(worker);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void delete( WorkerData worker) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(worker));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}

