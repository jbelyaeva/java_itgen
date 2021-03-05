package data.services;

import data.dao.FinishedChildLessonDao;
import data.model.schedule.FinishedChildLessonData;

public class FinishedChildLessonService {

  private final FinishedChildLessonDao finishedChildLessonDao = new FinishedChildLessonDao();

  public FinishedChildLessonService() {}

  public void save(FinishedChildLessonData finishedChildLesson) {
    finishedChildLessonDao.save(finishedChildLesson);
  }

  public FinishedChildLessonData DeleteByIdStudent(String idStudent) {
    return finishedChildLessonDao.deleteById(idStudent);
  }

  public void drop() {
    finishedChildLessonDao.drop();
  }
}
