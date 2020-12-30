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
import java.util.Date;

public class TrScheduleTodayHelper {

  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();
  private final ArrayList<Slots> slots = new ArrayList<>();

  public void SingleScheduleWithOneStudentOnTrail(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind("trial")
                                    .withS("normal")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void StartSingleScheduleWithOneStudentOnTrail(
      Double alreadyRun,
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind("trial")
                                    .withS("normal")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))
                        .withStartedAt((double) new Date().getTime() - alreadyRun)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void StartSingleScheduleWithOneStudentOnNewSkill(
      Double alreadyRun,
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withNewSubj(true)
                                    .withKind("oneTime")
                                    .withS("normal")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))
                        .withStartedAt((double) new Date().getTime() - alreadyRun)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void FinishedSingleScheduleWithOneStudentOnTrail(
      Double alreadyRun,
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang,
      String status) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(slots)
            .withFinishedSlots(
                Arrays.asList(
                    new FinishedSlots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withS(status)
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind("trial")
                                    .withScore(0)
                                    .withRating(0)
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))
                        .withStartedAt((double) new Date().getTime() - alreadyRun)
                        .withFinishedAt((double) new Date().getTime())))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void SingleScheduleWithOneStudentStartedSeveralHours(
      long hoursImMs,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    String period = time.getPeriod(time.getTimeNow() + hoursImMs);
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(
                new Slots()
                    .withId(idTrainer)
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(
                        Arrays.asList(
                            new C()
                                .withId(idStudent)
                                .withType(3)
                                .withSubject(idSubject)
                                .withLang(lang)
                                .withKind("oneTime")
                                .withScore(0)
                                .withRating(0)
                                .withS("normal")
                                .withStartTime(time.Stime(period))
                                .withEndTime(time.Etime(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }
}
