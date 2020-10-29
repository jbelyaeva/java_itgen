package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.PaymentData;
import io.itgen.model.materials.MaterialData;

public class MaterialDao {

  public void save(MaterialData material) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(material);
  }

  public MaterialData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<MaterialData> query = datastore.createQuery(MaterialData.class).filter("id", id);
    MaterialData material = datastore.findAndDelete(query);
    return material;
  }

  public MaterialData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialData> query = datastore.createQuery(MaterialData.class);
    datastore.delete(query);
  }
}
