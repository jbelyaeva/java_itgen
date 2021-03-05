package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.family.FamilyData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class FamilyDao implements Dao<FamilyData> {

  @Override
  public FamilyData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class).filter("id", id);
    FamilyData family = datastore.findAndDelete(query);
    return family;
  }

  @Override
  public void save(FamilyData family) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(family);
  }

  @Override
  public void delete(FamilyData family) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.delete(family);
  }

  @Override
  public void deleteField(String idFamily, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class).field("id").equal(idFamily);
    datastore.update(query, datastore.createUpdateOperations(FamilyData.class).unset(nameField));
  }

  @Override
  public <E> void updateField(String idFamily, String nameFiled, E data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class).filter("id", idFamily);
    UpdateOperations ops = datastore.createUpdateOperations(FamilyData.class).set(nameFiled, data);
    datastore.update(query, (UpdateOperations<FamilyData>) ops);
  }

  @Override
  public <E> void updateArrayField(String idStudent, String nameField, E[] data) {
  }

  @Override
  public FamilyData findById(String id) {
    return null;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class);
    datastore.delete(query);
  }
}
