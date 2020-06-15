package io.itgen.tests.schedule;
//автотест проверяет подвижку разового расписания

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.itgen.general.TimeGeneral;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.model.ScheduleData;
import io.itgen.model.Schedules;
import io.itgen.services.ScheduleService;
import io.itgen.tests.TestBase;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleBadMoveTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  Schedules after = null;
  Schedules before = null;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("scheduleSingleMove")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1").withOneTime(true);
    scheduleService.save(schedule);

  }

  @Test // нельзя подвинуть на время в прошлом
  public void testBadTimeScheduleSingleMove() {
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().badMove("scheduleSingleMove");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();
  }

  @Test // нельзя подвинуть не поменяв дату и время
  public void testNoChangeDateTimeScheduleSingleMove() {
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().badMoveNotChangeDateTime("scheduleSingleMove");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();

  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("scheduleSingleMove");
  }

}
