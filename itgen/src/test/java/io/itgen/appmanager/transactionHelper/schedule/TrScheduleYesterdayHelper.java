package io.itgen.appmanager.transactionHelper.schedule;

import io.itgen.general.TimeGeneral;
import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.FinishedSlots;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.services.ScheduleService;
import java.util.ArrayList;
import java.util.Arrays;

public class TrScheduleYesterdayHelper {
  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();
  // вчера завершенное первое пробное занятие (ученик русский)
  public void FinishingFirstTrialLesson(
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
                                    .withTrial(true)
                                    .withS("finished")
                                    .withScore(3)
                                    .withRating(4)))
                        .withStartedAt(time.StimeYesterday(periodFinish))
                        .withFinishedAt(time.EtimeYesterday(periodFinish))))
            .withTimes(
                new Times().withStart(time.start(periodFinish)).withEnd(time.finish(periodFinish)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
  }
}
