package ru.stqa.pft.itgen.dao;

import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class FamilyDao {

  public void delete(FamilyData family) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
   // entityManager.find(FamilyData.class,id);
    entityManager.remove(entityManager.merge(family));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
  public void merge(FamilyData family) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.merge(family);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

   public void create(FamilyData family) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(family);
    entityManager.getTransaction().commit();
    entityManager.close();

  }
  public FamilyData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    FamilyData family = entityManager.find(FamilyData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return family;
  }
}
