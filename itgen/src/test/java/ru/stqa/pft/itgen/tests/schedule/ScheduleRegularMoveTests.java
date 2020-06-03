package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет подвижку разового расписания

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.general.TimeGeneral;
import ru.stqa.pft.itgen.model.Schedule.C;
import ru.stqa.pft.itgen.model.Schedule.ST;
import ru.stqa.pft.itgen.model.Schedule.Slots;
import ru.stqa.pft.itgen.model.Schedule.Times;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleRegularMoveTests extends TestBase {
  ArrayList<C> list = new ArrayList();
  String period = "21:00 - 23:00";
  int week = 604800000;
  Schedules after = null;
  Schedules before = null;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData  schedule = new ScheduleData()
            .withId("scheduleRegularMove")
            .withVer(0)
            .withFromDate(time.time(period))
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.time(period))
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.time(period) + week)
                    .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.time(period) + week * 2)
                    .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.time(period) + week * 3)
                    .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.time(period) + week * 4)
                    .withSt(new ST().withS(time.Stime(period) + week * 4).withE(time.Etime(period) + week * 4))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.time(period) + week * 5)
                    .withSt(new ST().withS(time.Stime(period) + week * 5).withE(time.Etime(period) + week * 5))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);

  }

  @Test
  public void testScheduleRegularMove() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().move("scheduleRegularMove");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()+1));
    //продумать проверку на то, что время в расписании подвинулось верно
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    //при перемещении расписания: старое архивируется и создается новое
    String newSchedule = app.schedule().getNewScheduleDB(before, after);
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete(newSchedule);
    scheduleService.findByIdAndDelete("scheduleRegularMove");
  }
}
