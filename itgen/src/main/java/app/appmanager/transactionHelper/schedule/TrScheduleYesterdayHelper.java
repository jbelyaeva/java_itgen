package app.appmanager.transactionHelper.schedule;

import core.general.TimeGeneral;
import data.model.schedule.C;
import data.model.schedule.FinishedSlots;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.services.ScheduleService;
import java.util.ArrayList;
import java.util.Arrays;

public class TrScheduleYesterdayHelper {
  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();
  // вчера завершенное первое пробное занятие (ученик русский)
  public void finishingFirstTrialLesson(
      String periodFinish,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject) {
    ArrayList<Slots> listSlots = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateYesterday())
            .withSlots(listSlots)
            .withFinishedSlots(
                Arrays.asList(
                    new FinishedSlots()
                        .withId(idTrainer)
                        .withW(time.dateYesterday())
                        .withSt(
                            new ST()
                                .withS(time.StimeYesterday(periodFinish))
                                .withE(time.EtimeYesterday(periodFinish)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang("ru")
                                    .withKind("trial")
                                    .withS("finished")
                                    .withScore(3)
                                    .withRating(4)
                                    .withStartTime(time.StimeYesterday(periodFinish))
                                    .withEndTime(time.EtimeYesterday(periodFinish))))
                        .withStartedAt(time.StimeYesterday(periodFinish))
                        .withFinishedAt(time.EtimeYesterday(periodFinish))))
            .withTimes(
                new Times().withStart(time.start(periodFinish)).withEnd(time.finish(periodFinish)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void finishedLesson(
      String periodFinish,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      int type,
      String status,
      Boolean newSubject,
      String kind,
      Boolean wholness,
      String lang,
      int duration) {
    ArrayList<Slots> listSlots = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateYesterday())
            .withSlots(listSlots)
            .withFinishedSlots(
                Arrays.asList(
                    new FinishedSlots()
                        .withId(idTrainer)
                        .withW(time.dateYesterday())
                        .withSt(
                            new ST()
                                .withS(time.StimeYesterday(periodFinish))
                                .withE(time.EtimeYesterday(periodFinish)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(type)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind(kind)
                                    .withS(status)
                                    .withNewSubj(newSubject)
                                    .withStartTime(time.StimeYesterday(periodFinish))
                                    .withEndTime(time.EtimeYesterday(periodFinish))))
                        .withStartedAt(time.StimeYesterday(periodFinish))
                        .withFinishedAt(time.EtimeYesterday(periodFinish))))
            .withTimes(
                new Times().withStart(time.start(periodFinish)).withEnd(time.finish(periodFinish)))
            .withOneTime(true)
            .withDuration(duration)
            .withLessonFormat(0)
            .withWholeness(wholness);
    scheduleService.save(schedule);
  }
}
