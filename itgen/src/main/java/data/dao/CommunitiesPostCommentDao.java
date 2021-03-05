package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.communities.CommunitiesPostCommentData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CommunitiesPostCommentDao implements Dao<CommunitiesPostCommentData> {

  @Override
  public void save(CommunitiesPostCommentData communitiesPostComment) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(communitiesPostComment);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(CommunitiesPostCommentData communitiesPostCommentData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public CommunitiesPostCommentData deleteById(String id) {
    return null;
  }

  @Override
  public CommunitiesPostCommentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunitiesPostCommentData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunitiesPostCommentData> query = datastore
        .createQuery(CommunitiesPostCommentData.class);
    datastore.delete(query);
  }
}
