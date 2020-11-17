package tests.schedule;
// автотест проверяет подвижку разового расписания

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

public class ScheduleRegularMoveTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  int week = 604800000;
  Schedules after = null;
  Schedules before = null;
  String periodMove = "23:00 - 01:00";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule =
        new ScheduleData()
            .withId("scheduleRegularMove")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week)
                                .withE(time.Etime(period) + week))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 2)
                                .withE(time.Etime(period) + week * 2))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 3)
                                .withE(time.Etime(period) + week * 3))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 4)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 4)
                                .withE(time.Etime(period) + week * 4))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 5)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 5)
                                .withE(time.Etime(period) + week * 5))
                        .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleRegularMove() {
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().move(periodMove, "scheduleRegularMove");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size() + 1));
    app.goTo().menuTasks();
    // продумать проверку на то, что время в расписании подвинулось верно
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    // при перемещении расписания: старое архивируется и создается новое
    String newSchedule = app.schedule().getNewScheduleDB(before, after);
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById(newSchedule);
    scheduleService.DeleteById("scheduleRegularMove");
  }
}
