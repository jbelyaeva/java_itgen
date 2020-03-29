package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.Hike3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestConn {
  private static EntityManagerFactory entityManagerFactory;

  @BeforeMethod
  public static void setUpEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory( "hike-Pu" );
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.getTransaction().commit();
    // get a new EM to make sure data is actually retrieved from the store and not Hibernate’s internal cache
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
    List<Hike3> hikes = entityManager.createQuery( "SELECT h FROM Hike3 h" , Hike3.class ).getResultList();
    for (Hike3 group : hikes) {
      System.out.println(group);
    }
    entityManager.getTransaction().commit();
    entityManager.close();

  }
}