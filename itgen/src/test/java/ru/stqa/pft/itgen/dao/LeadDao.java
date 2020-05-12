package ru.stqa.pft.itgen.dao;

import ru.stqa.pft.itgen.model.LeadData;
import ru.stqa.pft.itgen.model.StudentData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class LeadDao {

  public LeadData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    LeadData lead = entityManager.find(LeadData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return lead;
  }

  public void create(LeadData lead) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(lead);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void delete(LeadData lead) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(lead));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
