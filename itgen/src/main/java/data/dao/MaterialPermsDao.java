package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import data.connection.MFSessionFactory;
import data.model.materials.MaterialPermsData;

public class MaterialPermsDao {

  public void save(MaterialPermsData materialPerms) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialPerms);
  }

  public MaterialPermsData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialPermsData.class).field("id").equal(id).first();
  }
}
