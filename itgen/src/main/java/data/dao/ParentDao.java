package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.users.ParentData;

public class ParentDao {

  public void save(ParentData parent) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(parent);
  }

  public ParentData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).filter("id", id);
    ParentData parent = datastore.findAndDelete(query);
    return parent;
  }

  public ParentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ParentData.class).field("id").equal(id).first();
  }
}
