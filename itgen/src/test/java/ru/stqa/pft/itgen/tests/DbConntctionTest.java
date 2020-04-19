package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DbConntctionTest {
    private static EntityManagerFactory entityManagerFactory;

    @BeforeMethod
    public static void setUpEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "connection" );
    }

    @Test
    public void testDBConnection() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /** эти запросы работают с "org.jboss.jbossts:jbossjta" */

        /* JP-QL запрос */
        String query1 = "from FamilyData";
        String query2 = "select h from StudentData h where firstname = 'Настя'";
        List<FamilyData> result = entityManager.createQuery( query1 , FamilyData.class ).getResultList();

        /* нативный запрос */
        String query3 = "{ $query : { roles : 'parent' } }";
//        List<ParentData> result = entityManager.createNativeQuery( query3 , ParentData.class ).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        for (FamilyData student : result) {
            System.out.println(student);
        }
    }

    @AfterMethod
    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}