package ru.stqa.pft.itgen.dao;

import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class ParentDao {

  public ParentData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    ParentData parent = entityManager.find(ParentData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return parent;
  }

  public void delete(ParentData parent) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(parent));
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void create(ParentData parent) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(parent);
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
