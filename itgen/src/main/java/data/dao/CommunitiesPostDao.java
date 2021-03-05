package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.communities.CommunitiesPostData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CommunitiesPostDao implements Dao<CommunitiesPostData> {

  @Override
  public void save(CommunitiesPostData communitiesPost) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(communitiesPost);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(CommunitiesPostData communitiesPostData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public CommunitiesPostData deleteById(String id) {
    return null;
  }

  @Override
  public CommunitiesPostData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunitiesPostData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunitiesPostData> query = datastore.createQuery(CommunitiesPostData.class);
    datastore.delete(query);
  }
}
