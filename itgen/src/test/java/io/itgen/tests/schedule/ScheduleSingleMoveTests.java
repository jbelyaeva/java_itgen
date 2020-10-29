package io.itgen.tests.schedule;
// автотест проверяет подвижку разового расписания

import io.itgen.general.RunTestAgain;
import io.itgen.general.TimeGeneral;
import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.Schedules;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.services.ScheduleService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleSingleMoveTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  String periodMove = "23:00 - 01:00";
  Schedules after = null;
  Schedules before = null;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule =
        new ScheduleData()
            .withId("scheduleSingleMove")
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
  public void testScheduleSingleMove() {
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().move(periodMove, "scheduleSingleMove");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size() + 1));
    app.goTo().menuTasks();
    // проверка, что подвинулось занятие
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    // при перемещении расписания: старое архивируется и создается новое
    String newSchedule = app.schedule().getNewScheduleDB(before, after);
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById(newSchedule);
    scheduleService.DeleteById("scheduleSingleMove");
  }
}
