package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.materials.MaterialChildData;

public class MaterialChildDao {

  public void save(MaterialChildData materialChild) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialChild);
  }

  public MaterialChildData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialChildData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialChildData> query = datastore.createQuery(MaterialChildData.class);
    datastore.delete(query);
  }
}
