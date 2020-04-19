package ru.stqa.pft.itgen.appmanager;

import ru.stqa.pft.itgen.model.Families;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.sql.Array;
import java.util.ArrayList;
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
}
