package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.materials.MaterialPermsData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class MaterialPermsDao implements Dao<MaterialPermsData> {

  @Override
  public void save(MaterialPermsData materialPerms) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialPerms);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialPermsData> query = datastore.createQuery(MaterialPermsData.class)
        .filter("id", id);
    UpdateOperations ops = datastore.createUpdateOperations(MaterialPermsData.class)
        .set(nameField, data);
    datastore.update(query, (UpdateOperations<MaterialPermsData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialPermsData> query = datastore.createQuery(MaterialPermsData.class)
        .filter("id", id);
    UpdateOperations ops = datastore.createUpdateOperations(MaterialPermsData.class)
        .set(nameField, data);
    datastore.update(query, (UpdateOperations<MaterialPermsData>) ops);
  }

  @Override
  public MaterialPermsData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialPermsData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {

  }

  @Override
  public void deleteField(String idPerm, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialPermsData> query = datastore.createQuery(MaterialPermsData.class)
        .field("id")
        .equal(idPerm);
    datastore.update(query,
        datastore.createUpdateOperations(MaterialPermsData.class).unset(nameField));
  }

  @Override
  public MaterialPermsData deleteById(String id) {
    return null;
  }

  @Override
  public void delete(MaterialPermsData perms) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.delete(perms);
  }
}
