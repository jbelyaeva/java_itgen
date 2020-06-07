package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.ParentData;
import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class ParentDao {

  public void save(ParentData parent) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(parent);
  }

  public ParentData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).filter("id", id);
    ParentData parent = datastore.findAndDelete(query);
    return parent;
  }
}
