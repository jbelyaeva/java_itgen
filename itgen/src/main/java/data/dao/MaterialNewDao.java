package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.materials.MaterialNewData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class MaterialNewDao implements Dao<MaterialNewData> {

  @Override
  public void save(MaterialNewData materialNew) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialNew);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(MaterialNewData materialNewData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public MaterialNewData deleteById(String id) {
    return null;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialNewData> query = datastore.createQuery(MaterialNewData.class);
    datastore.delete(query);
  }

  @Override
  public MaterialNewData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialNewData.class).field("id").equal(id).first();
  }
}
