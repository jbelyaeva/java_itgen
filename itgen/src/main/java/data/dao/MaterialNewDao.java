package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.materials.MaterialNewData;

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
