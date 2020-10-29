package io.itgen.services;

import io.itgen.dao.FinishedLessonDao;
import io.itgen.model.schedule.FinishedLessonData;

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
