package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.schedule.FinishedLessonData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class FinishedLessonDao implements Dao<FinishedLessonData> {

  @Override
  public void save(FinishedLessonData finishedLesson) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(finishedLesson);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(FinishedLessonData finishedLessonData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public FinishedLessonData deleteById(String id) {
    return null;
  }

  @Override
  public FinishedLessonData findById(String id) {
    return null;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FinishedLessonData> query = datastore.createQuery(FinishedLessonData.class);
    datastore.delete(query);
  }
}
