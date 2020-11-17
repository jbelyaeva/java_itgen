package tests.schedule;
// автотест проверяет назначение другого тренера (c id=18) в постоянном расписании на одно занятие и
// на все
// начальные данные: период, id тренера

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

public class ScheduleRegularAssignTrainerTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  int week = 604800000;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule =
        new ScheduleData()
            .withId("scheduleRegularAssignTrainer")
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
  public void testScheduleRegularAssignTrainer() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().assignTrainer("scheduleRegularAssignTrainer");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById("scheduleRegularAssignTrainer");
  }

  private void check(Schedules before, Schedules after) {
    TimeGeneral time = new TimeGeneral();
    ScheduleData scheduleAdd =
        new ScheduleData()
            .withId("scheduleRegularAssignTrainer")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("18")
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

    for (ScheduleData scheduleBefore : before) { // найти в списке "до" родителя с таким id
      if (scheduleBefore.getId().equals("scheduleRegularAssignTrainer")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
