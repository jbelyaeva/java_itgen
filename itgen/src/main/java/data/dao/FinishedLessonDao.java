package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.schedule.FinishedLessonData;

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
