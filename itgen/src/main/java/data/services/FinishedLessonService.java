package data.services;

import data.dao.FinishedLessonDao;
import data.model.schedule.FinishedLessonData;

public class FinishedLessonService {

  private final FinishedLessonDao finishedLessonDao = new FinishedLessonDao();

  public FinishedLessonService() {}

  public void save(FinishedLessonData finishedLesson) {
    finishedLessonDao.save(finishedLesson);
  }

  public void drop() {
    finishedLessonDao.drop();
  }
}
