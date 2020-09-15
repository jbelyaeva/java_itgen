package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.PaymentData;
import io.itgen.model.materials.MaterialChildData;
import io.itgen.model.materials.MaterialPermsData;

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
