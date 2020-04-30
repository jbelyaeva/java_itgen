package ru.stqa.pft.itgen.appmanager;

import ru.stqa.pft.itgen.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DBHelper {

  private final EntityManagerFactory entityManagerFactory;

  public DBHelper() {
    entityManagerFactory = Persistence.createEntityManagerFactory("connection");
  }

  public Students students() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : 'child'} }";
    List<StudentData> students = entityManager.createNativeQuery(query, StudentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Students(students);
  }

  public Families families() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    String query = "from FamilyData";
    List<FamilyData> families = entityManager.createQuery(query, FamilyData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Families(families);
  }

  //состав семьи
  public Students familyСomposition(String id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $or : [{ roles : 'child' }, {roles: 'parent'}], familyId:'" + id + "'}";
    List<StudentData> family = entityManager.createNativeQuery(query, StudentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Students(family);
  }

  public Parents parents() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : 'parent'} }";
    List<ParentData> parents = entityManager.createNativeQuery(query, ParentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Parents(parents);
  }

}
