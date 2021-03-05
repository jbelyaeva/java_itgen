package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.schedule.FinishedChildLessonData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import java.util.List;

public class FinishedChildLessonDao implements Dao<FinishedChildLessonData> {

  @Override
  public void save(FinishedChildLessonData finishedChildLesson) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(finishedChildLesson);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(FinishedChildLessonData finishedChildLessonData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public FinishedChildLessonData deleteById(String id) {
    return null;
  }

  @Override
  public FinishedChildLessonData findById(String id) {
    return null;
  }

  @Override
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
