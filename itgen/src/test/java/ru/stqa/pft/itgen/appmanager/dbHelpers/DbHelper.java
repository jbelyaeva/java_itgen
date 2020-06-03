package ru.stqa.pft.itgen.appmanager.dbHelpers;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.*;

import java.util.List;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class DbHelper {

  public Families families() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> q = datastore.createQuery(FamilyData.class);
    List<FamilyData> family = q.find().toList();
    return new Families(family);
  }

  public Students familyComposition(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> q = datastore.createQuery(StudentData.class);
    q.or(
            (q.and(q.criteria("roles").equal("child"), q.criteria("familyId").equal(id))),
            q.and(q.criteria("roles").equal("parent"), q.criteria("familyId").equal(id)));

    List<StudentData> students = q.find().toList();
    return new Students(students);
  }

  public Parents parents() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> q = datastore.createQuery(ParentData.class).filter("roles", "parent");
    List<ParentData> parents = q.find().toList();
    return new Parents(parents);
  }

  public Workers workers() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<WorkerData> q = datastore.createQuery(WorkerData.class);
    q.and(
            q.criteria("roles").notEqual("trainer"),
            q.criteria("roles").notEqual("child"),
            q.criteria("roles").notEqual("parent")
    );
    List<WorkerData> workers = q.find().toList();
    return new Workers(workers);
  }

  public Trainers trainers() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TrainerData> q = datastore.createQuery(TrainerData.class).filter("roles", "trainer");
    List<TrainerData> trainers = q.find().toList();
    return new Trainers(trainers);
  }

  public Leads leads() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<LeadData> q = datastore.createQuery(LeadData.class);
    List<LeadData> leads = q.find().toList();
    return new Leads(leads);
  }

  public LeadData find(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<LeadData> q = datastore.createQuery(LeadData.class).filter("id", id);
    LeadData lead = q.find().next();
    return lead;
  }
}
