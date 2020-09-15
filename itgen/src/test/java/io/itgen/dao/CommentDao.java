package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.schedule.CommentData;

public class CommentDao {

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommentData> query = datastore.createQuery(CommentData.class);
    datastore.delete(query);
  }
}

