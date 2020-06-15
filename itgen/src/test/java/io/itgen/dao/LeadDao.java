package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.LeadData;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

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
