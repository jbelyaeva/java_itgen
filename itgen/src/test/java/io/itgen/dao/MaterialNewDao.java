package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.materials.MaterialNewData;

public class MaterialNewDao {

  public void save(MaterialNewData materialNew) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialNew);
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialNewData> query = datastore.createQuery(MaterialNewData.class);
    datastore.delete(query);
  }
}
