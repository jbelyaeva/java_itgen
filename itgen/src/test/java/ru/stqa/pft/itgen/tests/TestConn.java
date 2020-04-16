package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.Hike3;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestConn {
  private static EntityManagerFactory entityManagerFactory;

  @BeforeMethod
  public static void setUpEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory( "connection" );
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.getTransaction().commit();
    // get a new EM to make sure data is actually retrieved from the store and not Hibernate internal cache
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

    List<StudentData> students = entityManager.createQuery( "SELECT h FROM StudentData h" , StudentData.class ).getResultList();
    for (StudentData student : students) {
      System.out.println(student);
    }
    entityManager.getTransaction().commit();
    entityManager.close();

  }
}