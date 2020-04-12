package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.StudentData1;
import ru.stqa.pft.itgen.model.StudentData2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestConn {
  private static EntityManagerFactory entityManagerFactory;

  @BeforeMethod
  public static void setUpEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory( "studentData" );
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.getTransaction().commit();
    // get a new EM to make sure data is actually retrieved from the store and not Hibernates internal cache
    entityManager.close();
  }

  @AfterMethod
  public static void closeEntityManagerFactory() {
    entityManagerFactory.close();
  }

  @Test
  public void canSearchUsingJPQLQuery() {
    // Get a new entityManager
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    // Start transaction
    entityManager.getTransaction().begin();
    // Find all the available hikes ordered by difficulty
    List<StudentData1> students = entityManager.createQuery( "SELECT n FROM StudentData1 n " , StudentData1.class ).getResultList();
    for (StudentData1 student : students) {
      System.out.println(student);
    }
    entityManager.getTransaction().commit();
    entityManager.close();

  }
}