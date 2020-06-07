package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.LeadData;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class LeadDao {

  public void save(LeadData lead) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(lead);
  }

  public LeadData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<LeadData> query = datastore.createQuery(LeadData.class).filter("id", id);
    LeadData lead = datastore.findAndDelete(query);
    return lead;
  }
}
