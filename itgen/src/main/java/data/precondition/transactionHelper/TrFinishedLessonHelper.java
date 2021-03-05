package data.precondition.transactionHelper;

import data.model.schedule.Childs;
import data.model.schedule.FinishedChildLessonData;
import data.model.schedule.FinishedLessonData;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import java.util.Arrays;

public class TrFinishedLessonHelper {
  private final FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  private final FinishedLessonService finishedLessonService = new FinishedLessonService();

  public void finishedChildLesson(String id,
      String idSchedule,
      Double week,
      int type,
      String idTrainer,
     String idChild,
      String status,
      int score,
      int duration,
      String skillId,
      String lang,
      int rating,
      Boolean isTrial,
      Boolean p,
      Double startTime,
      Double endTime
      ) {
    FinishedChildLessonData childLesson = new FinishedChildLessonData()
        .withChildId(id)
        .withScheduleId(idSchedule)
        .withWeek(week)
        .withType(type)
        .withTrainerId(idTrainer)
        .withChildId(idChild)
        .withStatus(status)
        .withScore(score)
        .withDuration(duration)
        .withSkillId(skillId)
        .withLang(lang)
        .withRating(rating)
        .withTrail(isTrial)
        .withP(p)
        .withStartTime(startTime)
        .withEndTime(endTime);
    finishedChildLessonService.save(childLesson);
  }


  public void finishedLessonWithOneStudent(
      String id,
      String idSchedule,
      Double week,
      int type,
      String idTrainer,
      Double startTime,
      Double endTime,
      Double startedAt,
      Double finishedAt,
      String idChild,
      String status,
      int score,
      int duration,
      String skillId,
      String lang,
      int rating,
      Boolean isTrial,
      Boolean p
  ) {
    FinishedLessonData lesson = new FinishedLessonData()
        .withId(id)
        .withScheduleId(idSchedule)
        .withWeek(week)
        .withType(type)
        .withTrainerId(idTrainer)
        .withStartTime(startTime)
        .withEndTime(endTime)
        .withChilds(Arrays.asList(new Childs()
            .withId(idChild)
            .withStatus(status)
            .withScore(score)
            .withDuration(duration)
            .withSkillId(skillId)
            .withLang(lang)
            .withRating(rating)
            .withTrail(isTrial)
            .withP(p)
            .withStartTime(startTime)
            .withEndTime(endTime)))
        .withStartedAt(startedAt)
        .withFinishedAt(finishedAt);
   finishedLessonService.save(lesson);
  }
}
