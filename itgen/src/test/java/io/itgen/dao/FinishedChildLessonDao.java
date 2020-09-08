package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.LeadData;
import io.itgen.model.StudentData;
import io.itgen.model.schedule.FinishedChildLessonData;
import io.itgen.model.schedule.FinishedLessonData;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
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

  public FinishedChildLessonData DeleteByIdStudent(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FinishedChildLessonData> query = datastore.createQuery(FinishedChildLessonData.class)
        .filter("childId", id);
    List<FinishedChildLessonData> finishedChildLessons = query.find().toList();
    FinishedChildLessonData finishedChildLesson = finishedChildLessons.stream().findFirst().get();
    return finishedChildLesson;
  }
}

