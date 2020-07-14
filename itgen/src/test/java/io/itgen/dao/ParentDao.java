package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.ParentData;
import io.itgen.model.StudentData;

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
