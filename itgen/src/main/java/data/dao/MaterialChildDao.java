package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.materials.MaterialChildData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class MaterialChildDao implements Dao<MaterialChildData> {

  @Override
  public void save(MaterialChildData materialChild) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialChild);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(MaterialChildData materialChildData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public MaterialChildData deleteById(String id) {
    return null;
  }

  @Override
  public MaterialChildData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialChildData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialChildData> query = datastore.createQuery(MaterialChildData.class);
    datastore.delete(query);
  }
}
