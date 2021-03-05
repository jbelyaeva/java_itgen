package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.communities.CommunityData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CommunitiesDao implements Dao<CommunityData> {

  @Override
  public void save(CommunityData community) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(community);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(CommunityData communityData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public CommunityData deleteById(String id) {
    return null;
  }

  @Override
  public CommunityData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunityData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunityData> query = datastore.createQuery(CommunityData.class);
    datastore.delete(query);
  }
}
