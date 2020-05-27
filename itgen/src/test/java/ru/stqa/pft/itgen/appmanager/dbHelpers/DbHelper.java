package ru.stqa.pft.itgen.appmanager.dbHelpers;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.*;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.stqa.pft.itgen.connection.HbSessionFactory.hibernateSessionFactoryUtil;
import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class DbHelper {

   public Families families() {
     Datastore datastore = morphiaSessionFactoryUtil();
     Query<FamilyData> query = datastore.createQuery(FamilyData.class);
     List<FamilyData> family = query.find().toList();
     return new Families(family);
  }


  public Students familyComposition(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> q = datastore.createQuery(StudentData.class);

    q.or(
            q.criteria("roles").equal("child").and( q.criteria("roles").equal("child")),
            q.criteria("familyId").equal(id)
    );

    List<StudentData> students = q.find().toList();
    return new Students(students);
  }

  public Parents parents() {
     Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).filter("roles", "parent");
    List<ParentData> parents = query.find().toList();
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
