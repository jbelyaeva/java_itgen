package data.precondition.transactionHelper.schedule;

import core.general.TimeGeneral;
import data.model.schedule.C;
import data.model.schedule.FinishedSlots;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.services.ScheduleService;
import java.util.ArrayList;
import java.util.List;

public class TrScheduleHelper {

  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();

  private List<Slots> generateSlots(String period, String idTrainer, ArrayList<List<C>> listsC,
      int correctDaysInDate) {
    int week = 604800000;
    ArrayList<Slots> slots = new ArrayList<>();
    for (int i = 0; i < listsC.size(); i++) {
      slots.add(
          new Slots()
              .withId(idTrainer)
              .withW(time.dateTargetDays(correctDaysInDate) + week * i)
              .withSt(
                  new ST()
                      .withS(time.StimeTargetDay(period, 30) + week * i)
                      .withE(time.EtimeTargetDay(period, 30) + week * i))
              .withC(listsC.get(i)));
    }
    return slots;
  }

  // Разовое занятие без учеников
  public void SingleScheduleWithoutStudent(String period, String idSchedule, String idTrainer,
      int correctDaysInDate) {
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    for (int i = 0; i < 1; i++) {
      listsC.add(new ArrayList<>());
    }
    ArrayList<FinishedSlots> listFSlots = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTargetDays(correctDaysInDate))
            .withSlots(this.generateSlots(period, idTrainer, listsC, correctDaysInDate))
            .withFinishedSlots(listFSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }
}
