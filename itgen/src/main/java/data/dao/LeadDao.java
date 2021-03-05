package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.lead.LeadData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class LeadDao implements Dao<LeadData> {

  @Override
  public void save(LeadData lead) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(lead);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(LeadData leadData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public LeadData deleteById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<LeadData> query = datastore.createQuery(LeadData.class).filter("id", id);
    LeadData lead = datastore.findAndDelete(query);
    return lead;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<LeadData> query = datastore.createQuery(LeadData.class);
    datastore.delete(query);
  }

  @Override
  public LeadData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(LeadData.class).field("id").equal(id).first();
  }

  public LeadData findByIdAndDelete(LeadData lead) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<LeadData> query = datastore.createQuery(LeadData.class).filter("id", lead.getId());
    LeadData leadData = datastore.findAndDelete(query);
    return leadData;
  }
}
