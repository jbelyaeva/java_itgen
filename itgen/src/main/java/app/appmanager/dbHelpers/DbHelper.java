package app.appmanager.dbHelpers;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.family.Families;
import data.model.family.FamilyData;
import data.model.lead.LeadData;
import data.model.lead.Leads;
import data.model.payment.PaymentData;
import data.model.payment.Payments;
import data.model.skills.SkillsData;
import data.model.typeform.TestData;
import data.model.users.ParentData;
import data.model.users.Parents;
import data.model.users.StudentData;
import data.model.users.Students;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.model.users.WorkerData;
import data.model.users.Workers;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

  Datastore datastore = morphiaSessionFactoryUtil();

  public Families families() {
    Query<FamilyData> q = datastore.createQuery(FamilyData.class);
    List<FamilyData> family = q.find().toList();
    return new Families(family);
  }

  public Parents parents() {
    Query<ParentData> q = datastore.createQuery(ParentData.class).filter("roles", "parent");
    List<ParentData> parents = q.find().toList();
    return new Parents(parents);
  }

  public FamilyData lastFamily() {
    Query<FamilyData> q = datastore.createQuery(FamilyData.class);
    long count = q.count();
    List<FamilyData> family = q.find().toList();
    FamilyData lastFamily = family.get(Math.toIntExact(count - 1));
    return lastFamily;
  }

  public ParentData lastParent() {
    Query<ParentData> q = datastore.createQuery(ParentData.class).filter("roles", "parent");
    long count = q.count();
    List<ParentData> parent = q.find().toList();
    ParentData lastParent = parent.get(Math.toIntExact(count - 1));
    return lastParent;
  }

  public Students familyComposition(String id) {
    Query<StudentData> q = datastore.createQuery(StudentData.class);
    q.or(
        (q.and(q.criteria("roles").equal("child"), q.criteria("familyId").equal(id))),
        q.and(q.criteria("roles").equal("parent"), q.criteria("familyId").equal(id)));

    List<StudentData> students = q.find().toList();
    return new Students(students);
  }

  public ParentData getTokenParent(String name, String surname, String role) {
    Query<ParentData> q = datastore.createQuery(ParentData.class);

    q.and(
        q.criteria("roles").equal(role),
        q.criteria("firstName").equal(name),
        q.criteria("lastName").equal(surname));
    List<ParentData> parents = q.find().toList();
    return q.find().next();
  }

  public Workers workers() {
    Query<WorkerData> q = datastore.createQuery(WorkerData.class);
    q.and(
        q.criteria("roles").notEqual("trainer"),
        q.criteria("roles").notEqual("child"),
        q.criteria("roles").notEqual("parent"));
    List<WorkerData> workers = q.find().toList();
    return new Workers(workers);
  }

  public Trainers trainers() {
    Query<TrainerData> q = datastore.createQuery(TrainerData.class).filter("roles", "trainer");
    List<TrainerData> trainers = q.find().toList();
    return new Trainers(trainers);
  }

  public TrainerData lastTrainer() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TrainerData> q = datastore.createQuery(TrainerData.class);
    long count = q.count();
    List<TrainerData> trainer = q.find().toList();
    TrainerData lastTrainer = trainer.get(Math.toIntExact(count - 1));
    return lastTrainer;
  }

  public Leads leads() {
    Query<LeadData> q = datastore.createQuery(LeadData.class);
    List<LeadData> leads = q.find().toList();
    return new Leads(leads);
  }

  public LeadData find(String id) {
    Query<LeadData> q = datastore.createQuery(LeadData.class).filter("id", id);
    LeadData lead = q.find().next();
    return lead;
  }

  public Payments payments(String idFamily) {
    Query<PaymentData> q = datastore.createQuery(PaymentData.class).filter("fId", idFamily);
    List<PaymentData> payments = q.find().toList();
    return new Payments(payments);
  }

  public TestData lastTest() {
    Query<TestData> q = datastore.createQuery(TestData.class);
    long count = q.count();
    return q.find().toList().get(Math.toIntExact(count - 1));
  }

  public SkillsData firstSkill() {
    return datastore.createQuery(SkillsData.class).find().toList().get(0);
  }

  public List<String> roles(String idWorker) {
    if (idWorker.equals("666")) {
      ArrayList<String> roles;
      roles = new ArrayList<String>();
      roles.add("admin");
      return roles;
    } else {
      Query<WorkerData> q = datastore.createQuery(WorkerData.class).filter("_id", idWorker);
      WorkerData workers = q.find().next();
      List<String> roles = workers.getRoles();
      return roles;
    }
  }
}
