package io.itgen.services;

import io.itgen.dao.FinishedChildLessonDao;
import io.itgen.model.LeadData;
import io.itgen.model.StudentData;
import io.itgen.model.schedule.FinishedChildLessonData;
import io.itgen.model.tasks.TaskData;

public class FinishedChildLessonService {

  private final FinishedChildLessonDao finishedChildLessonDao = new FinishedChildLessonDao();

  public FinishedChildLessonService() {
  }

  public void save(FinishedChildLessonData finishedChildLesson) {
    finishedChildLessonDao.save(finishedChildLesson);
  }

  public FinishedChildLessonData DeleteByIdStudent(String idStudent) {
    return finishedChildLessonDao.DeleteByIdStudent(idStudent);
  }

  public void drop() {
    finishedChildLessonDao.drop();
  }
}
