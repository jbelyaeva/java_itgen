package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.schedule.FinishedLessonData;

public class FinishedLessonDao {

  public void save(FinishedLessonData finishedLesson) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(finishedLesson);
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FinishedLessonData> query = datastore.createQuery(FinishedLessonData.class);
    datastore.delete(query);
  }
}
