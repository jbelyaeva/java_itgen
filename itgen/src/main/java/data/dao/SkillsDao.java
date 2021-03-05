package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.skills.SkillsData;
import data.model.skills.SkillsOrderData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class SkillsDao implements Dao<SkillsData> {

  @Override
  public void save(SkillsData skill) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(skill);
  }

  @Override
  public <T> void updateField(String idSkill, String nameField, T data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsData> query = datastore.createQuery(SkillsData.class).filter("id", idSkill);
    UpdateOperations ops = datastore.createUpdateOperations(SkillsData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<SkillsData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(SkillsData skillsData) {

  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsData> query = datastore.createQuery(SkillsData.class);
    datastore.delete(query);
  }

  public void dropSkillOrder() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsOrderData> query = datastore.createQuery(SkillsOrderData.class);
    datastore.delete(query);
  }

  public void deleteField(String idSkills, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsData> query = datastore.createQuery(SkillsData.class).field("id").equal(idSkills);
    datastore.update(query, datastore.createUpdateOperations(SkillsData.class).unset(name));
  }

  @Override
  public SkillsData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<SkillsData> query = datastore.createQuery(SkillsData.class).filter("id", id);
    SkillsData skill = datastore.findAndDelete(query);
    return skill;
  }

  @Override
  public SkillsData findById(String id) {
    return null;
  }

  public SkillsData findBySkillId(String skillId, String lang) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsData> q = datastore.createQuery(SkillsData.class);
    q.and(
        q.criteria("skillId").equal(skillId),
        q.criteria("lang").equal(lang));
    SkillsData skill = q.find().next();
    return skill;
  }

}
