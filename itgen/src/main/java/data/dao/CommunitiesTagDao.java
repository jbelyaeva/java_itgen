package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.communities.CommunitiesTagData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CommunitiesTagDao implements Dao<CommunitiesTagData> {

  @Override
  public void save(CommunitiesTagData communitiesTag) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(communitiesTag);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(CommunitiesTagData communitiesTagData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public CommunitiesTagData deleteById(String id) {
    return null;
  }

  @Override
  public CommunitiesTagData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunitiesTagData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunitiesTagData> query = datastore.createQuery(CommunitiesTagData.class);
    datastore.delete(query);
  }
}
