package ru.stqa.pft.itgen.tests.testing;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DbConnectionTest {
  private static EntityManagerFactory entityManagerFactory;

  @BeforeMethod
  public static void setUpEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongoDB");
  }

  @Test
  public void testDBConnection() throws Exception {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    /** эти запросы работают с "org.jboss.jbossts:jbossjta" */

    /* JP-QL запрос */
    String query1 = "from StudentData";
    String query2 = "select h from StudentData h where firstname = 'Настя'";
    List<StudentData> result = entityManager.createQuery(query1, StudentData.class).getResultList();

    /* нативный запрос */
    String query3 = "{ $query : { roles : 'child' } }";
    String query4 = "{ $or : [{ roles : 'child' }, {roles: 'parent'}]}"; // выводит всех учеников и родителей
//    List<StudentData> result = entityManager.createNativeQuery(query4, StudentData.class).getResultList();

    entityManager.getTransaction().commit();
    entityManager.close();

    for (StudentData student : result) {
      System.out.println(student);

      //  System.out.println(student.getVer());
      //  System.out.println(student.getTimes());
      //   System.out.println(student.getSlots());
//            System.out.println(family.getStudentsS());
//            System.out.println(family.getParentsP());
    }
  }

  @AfterMethod
  public static void closeEntityManagerFactory() {
    entityManagerFactory.close();
  }
}