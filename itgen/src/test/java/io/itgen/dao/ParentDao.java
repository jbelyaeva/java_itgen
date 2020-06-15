package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.ParentData;

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
}
