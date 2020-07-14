package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.LeadData;
import io.itgen.model.ScheduleData;
import io.itgen.model.StudentData;

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

  public LeadData findByIdAndDelete(LeadData lead) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<LeadData> query = datastore.createQuery(LeadData.class)
        .filter("id", lead.getId());
    LeadData leadData = datastore.findAndDelete(query);
    return leadData;
  }

  public LeadData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(LeadData.class).field("id").equal(id).first();
  }
}
