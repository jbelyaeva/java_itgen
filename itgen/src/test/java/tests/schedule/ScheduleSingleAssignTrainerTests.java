package tests.schedule;
// автотест проверяет назначение другого тренера в разовом расписании

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.schedule.C;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.services.ScheduleService;
import app.testbase.TestBase;
import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleSingleAssignTrainerTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule =
        new ScheduleData()
            .withId("scheduleSingleAssignTrainer")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleSingleAssignTrainer() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().assignTrainer("scheduleSingleAssignTrainer");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    // проверка, что назначен новый тренер и остальные записи не изменились
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById("scheduleSingleAssignTrainer");
  }

  private void check(Schedules before, Schedules after) {
    TimeGeneral time = new TimeGeneral();
    ScheduleData scheduleAdd =
        new ScheduleData()
            .withId("scheduleSingleAssignTrainer")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("18")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);

    for (ScheduleData scheduleBefore : before) { // найти в списке "до" родителя с таким id
      if (scheduleBefore.getId().equals("scheduleSingleAssignTrainer")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
