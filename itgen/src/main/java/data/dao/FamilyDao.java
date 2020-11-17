package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import data.connection.MFSessionFactory;
import data.model.family.FamilyData;

public class FamilyDao {

  public FamilyData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class).filter("id", id);
    FamilyData family = datastore.findAndDelete(query);
    return family;
  }

  public void save(FamilyData family) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(family);
  }

  public void delete(FamilyData family) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.delete(family);
  }

  public void updateFieldBoolean(String idFamily, String nameFiled, Boolean data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class).filter("id", idFamily);
    UpdateOperations ops = datastore.createUpdateOperations(FamilyData.class).set(nameFiled, data);
    datastore.update(query, (UpdateOperations<FamilyData>) ops);
  }
}
