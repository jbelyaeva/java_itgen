package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.skills.SkillsOrderData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class SkillsOrderDao implements Dao<SkillsOrderData> {

  @Override
  public void save(SkillsOrderData skillOrder) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(skillOrder);
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsOrderData> query = datastore.createQuery(SkillsOrderData.class);
    datastore.delete(query);
  }

  @Override
  public <T> void updateField(String idSkill, String nameFiled, T data) {
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(SkillsOrderData skillsOrderData) {

  }

  @Override
  public void deleteField(String idSkills, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsOrderData> query = datastore.createQuery(SkillsOrderData.class)
        .field("id")
        .equal(idSkills);
    datastore.update(query, datastore.createUpdateOperations(SkillsOrderData.class).unset(name));
  }

  @Override
  public SkillsOrderData deleteById(String id) {
    return null;
  }

  @Override
  public SkillsOrderData findById(String id) {
    return null;
  }
}
