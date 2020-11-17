package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.model.schedule.CommentData;

public class CommentDao {

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommentData> query = datastore.createQuery(CommentData.class);
    datastore.delete(query);
  }
  public void save(CommentData comment) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(comment);
  }
}
