package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.schedule.CommentData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CommentDao implements Dao<CommentData> {

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommentData> query = datastore.createQuery(CommentData.class);
    datastore.delete(query);
  }

  @Override
  public void save(CommentData comment) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(comment);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(CommentData commentData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public CommentData deleteById(String id) {
    return null;
  }

  @Override
  public CommentData findById(String id) {
    return null;
  }
}
