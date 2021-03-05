package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.materials.MaterialData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class MaterialDao implements Dao<MaterialData> {

  @Override
  public void save(MaterialData material) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(material);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(MaterialData materialData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public MaterialData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<MaterialData> query = datastore.createQuery(MaterialData.class).filter("id", id);
    MaterialData material = datastore.findAndDelete(query);
    return material;
  }

  @Override
  public MaterialData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialData> query = datastore.createQuery(MaterialData.class);
    datastore.delete(query);
  }
}
