package ru.stqa.pft.itgen.dao;

import ru.stqa.pft.itgen.model.FamilyData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class FamilyDao {

  public FamilyData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    FamilyData family = entityManager.find(FamilyData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return family;
  }

  public void create(FamilyData family) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(family);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void delete(FamilyData family) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(family));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
