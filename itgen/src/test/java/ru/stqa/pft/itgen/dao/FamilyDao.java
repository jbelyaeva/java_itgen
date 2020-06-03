package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.FamilyData;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class FamilyDao {

  public FamilyData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class)
            .filter("id", id);
    FamilyData family = datastore.findAndDelete(query);
    return family;
  }

  public void save(FamilyData family) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(family);
  }

  public void delete(FamilyData family) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.delete(family);
  }

}
