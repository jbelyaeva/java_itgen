package ru.stqa.pft.itgen.dao;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.WorkerData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class TrainerDao {

  public TrainerData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    TrainerData trainer = entityManager.find( TrainerData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return trainer;
  }

  public void create( TrainerData trainer) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(trainer);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void delete( TrainerData trainer) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(trainer));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}

