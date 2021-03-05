package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.users.ParentData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class ParentDao implements Dao<ParentData> {

  @Override
  public void save(ParentData parent) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(parent);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(ParentData parentData) {

  }

  @Override
  public ParentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ParentData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {

  }

  @Override
  public void deleteField(String idParent, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).field("id").equal(idParent);
    datastore.update(query, datastore.createUpdateOperations(ParentData.class).unset(nameField));
  }

  @Override
  public ParentData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).filter("id", id);
    ParentData parent = datastore.findAndDelete(query);
    return parent;
  }
}
