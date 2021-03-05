package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.tier.TierCountryData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class TierCountryDao implements Dao<TierCountryData> {

  @Override
  public void save(TierCountryData tierCountryData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(tierCountryData);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(TierCountryData tierCountryData) {

  }

  @Override
  public void deleteField(String id, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TierCountryData> query = datastore.createQuery(TierCountryData.class)
        .field("id")
        .equal(id);
    datastore.update(query,
        datastore.createUpdateOperations(TierCountryData.class).unset(nameField));
  }

  @Override
  public TierCountryData deleteById(String id) {
    return null;
  }

  @Override
  public TierCountryData findById(String id) {
    return null;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TierCountryData> query = datastore.createQuery(TierCountryData.class);
    datastore.delete(query);
  }
}
