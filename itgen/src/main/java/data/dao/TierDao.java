package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.tier.TierData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class TierDao implements Dao<TierData> {

  @Override
  public void save(TierData tierData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(tierData);
  }

  @Override
  public <T> void updateField(String idStudent, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String idStudent, String nameField, T[] data) {

  }

  @Override
  public void delete(TierData tierData) {

  }

  @Override
  public void deleteField(String id, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TierData> query = datastore.createQuery(TierData.class).field("id").equal(id);
    datastore.update(query, datastore.createUpdateOperations(TierData.class).unset(nameField));
  }

  @Override
  public TierData deleteById(String id) {
    return null;
  }

  @Override
  public TierData findById(String id) {
    return null;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TierData> query = datastore.createQuery(TierData.class);
    datastore.delete(query);
  }
}
