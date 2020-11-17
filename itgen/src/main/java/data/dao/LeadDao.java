package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.lead.LeadData;

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
    Query<LeadData> query = datastore.createQuery(LeadData.class).filter("id", lead.getId());
    LeadData leadData = datastore.findAndDelete(query);
    return leadData;
  }

  public LeadData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(LeadData.class).field("id").equal(id).first();
  }
}
