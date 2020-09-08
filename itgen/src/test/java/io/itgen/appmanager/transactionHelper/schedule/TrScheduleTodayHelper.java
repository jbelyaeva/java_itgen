package io.itgen.appmanager.transactionHelper.schedule;

import io.itgen.general.TimeGeneral;
import io.itgen.model.schedule.FinishedSlots;
import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.services.ScheduleService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TrScheduleTodayHelper {
  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();
  private ArrayList<Slots> slots = new ArrayList<>();

  public void SingleScheduleWithOneStudentOnTrail(String period,String idSchedule, String idTrainer,
      String idStudent,String idSubject,String lang) {
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
                          .withSt(
                              new ST()
                                  .withS(time.Stime(period))
                                  .withE(time.Etime(period)))
                          .withC(
                              Arrays.asList(
                                  new C()
                                      .withS("normal")
                                      .withId(idStudent)
                                      .withType(3)
                                      .withSubject(idSubject)
                                      .withLang(lang)
                                      .withTrial(true)
                                      .withS("normal")))))
              .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
              .withSkypeId("1")
              .withOneTime(true);
      scheduleService.save(schedule);
  }

  public void StartSingleScheduleWithOneStudentOnTrail(Double alreadyRun, String period,String idSchedule, String idTrainer,
      String idStudent,String idSubject,String lang) {
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
                        .withSt(
                            new ST()
                                .withS(time.Stime(period))
                                .withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withTrial(true)
                                    .withS("normal")))
                .withStartedAt((double) new Date().getTime()-alreadyRun)
                ))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void FinishedSingleScheduleWithOneStudentOnTrail(Double alreadyRun, String period,String idSchedule, String idTrainer,
      String idStudent,String idSubject,String lang,String status) {
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
                        .withSt(
                            new ST()
                                .withS(time.Stime(period))
                                .withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withS(status)
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withTrial(true)
                                    .withP(false)
                                    .withScore(0)
                                    .withRating(0)))
                        .withStartedAt((double) new Date().getTime()-alreadyRun)
                        .withFinishedAt((double) new Date().getTime())))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
  }
}
