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
