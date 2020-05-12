package ru.stqa.pft.itgen.appmanager;

import ru.stqa.pft.itgen.model.*;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;

public class DbHelper {

 // private final EntityManagerFactory entityManagerFactory;

 /* public DbHelper()  {
    entityManagerFactory = Persistence.createEntityManagerFactory("connection");
  }*/

  public Students students() {
    EntityManager entityManager =hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : 'child'} }";
    List<StudentData> students = entityManager.createNativeQuery(query, StudentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Students(students);
  }

  public Families families() {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "from FamilyData";
    List<FamilyData> families = entityManager.createQuery(query, FamilyData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Families(families);
  }

  public Students familyComposition(String id) {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $or : [{ roles : 'child' }, {roles: 'parent'}], familyId:'" + id + "'}";
    List<StudentData> family = entityManager.createNativeQuery(query, StudentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Students(family);
  }

  public Parents parents() {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : 'parent'} }";
    List<ParentData> parents = entityManager.createNativeQuery(query, ParentData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Parents(parents);
  }

  public Workers workers() {
    EntityManager entityManager = hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : {$nin :['trainer','child','parent']}}}";
    List<WorkerData> workers = entityManager.createNativeQuery(query, WorkerData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Workers(workers);
  }

  public Trainers trainers() {
    EntityManager entityManager =hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "{ $query : { roles : 'trainer'} }";
    List<TrainerData> trainers = entityManager.createNativeQuery(query, TrainerData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Trainers(trainers);
  }
  public Leads leads() {
    EntityManager entityManager =hibernateSessionFactoryUtil().createEntityManager();
    entityManager.getTransaction().begin();
    String query = "from LeadData";
    List<LeadData> leads = entityManager.createQuery(query, LeadData.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return new Leads(leads);
  }

}
