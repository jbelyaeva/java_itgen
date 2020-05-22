package ru.stqa.pft.itgen.appmanager;

import ru.stqa.pft.itgen.model.*;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class DbHelperStudents {

  public Students students() {
    EntityManager entityManager =hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : 'child'} }";
    List<StudentData> students = entityManager.createNativeQuery(query, StudentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Students(students);
  }
/*
  public Families families() {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "from FamilyData";
    List<FamilyData> families = entityManager.createQuery(query, FamilyData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Families(families);
  }
*/
 /*
  public Students familyComposition(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $or : [{ roles : 'child' }, {roles: 'parent'}], familyId:'" + id + "'}";
    List<StudentData> family = entityManager.createNativeQuery(query, StudentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Students(family);
  }*/

  public Parents parents() {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : 'parent'} }";
    List<ParentData> parents = entityManager.createNativeQuery(query, ParentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Parents(parents);
  }

   public Students studentFiltrPol(int pol) {
    EntityManager entityManager =hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query ="{ $and : [{ roles : 'child'}], gender:" + pol + "}";
    List<StudentData> students = entityManager.createNativeQuery(query, StudentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Students(students);


  }

}
