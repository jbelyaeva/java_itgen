package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.materials.MaterialPerms;
import io.itgen.model.materials.MaterialPermsData;

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
