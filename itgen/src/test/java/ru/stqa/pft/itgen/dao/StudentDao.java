package ru.stqa.pft.itgen.dao;

import ru.stqa.pft.itgen.model.StudentData;

import javax.persistence.EntityManager;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class StudentDao {

  public StudentData findById(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    StudentData student = entityManager.find(StudentData.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return student;
  }

  public void delete(StudentData student) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.merge(student));
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void create(StudentData student) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(student);
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
