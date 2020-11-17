package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.schedule.FinishedChildLessonData;
import java.util.List;

public class FinishedChildLessonDao {

  public void save(FinishedChildLessonData finishedChildLesson) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(finishedChildLesson);
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FinishedChildLessonData> query = datastore.createQuery(FinishedChildLessonData.class);
    datastore.delete(query);
  }

  public FinishedChildLessonData FindByIdStudent(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FinishedChildLessonData> query =
        datastore.createQuery(FinishedChildLessonData.class).filter("childId", id);
    List<FinishedChildLessonData> finishedChildLessons = query.find().toList();
    FinishedChildLessonData finishedChildLesson = finishedChildLessons.stream().findFirst().get();
    return finishedChildLesson;
  }
}
