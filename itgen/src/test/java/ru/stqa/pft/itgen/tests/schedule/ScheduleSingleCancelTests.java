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

public class ScheduleSingleCancelTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("scheduleSingleCancel")
            .withVer(0)
            .withFromDate(time.time(period))
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.time(period))
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1").withOneTime(true);
    scheduleService.save(schedule);

  }

  @Test
  public void testScheduleSingleCancel() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().cancel("scheduleSingleCancel");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(after, before);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("scheduleSingleCancel");
  }

  private void check(Schedules after, Schedules before) {
    TimeGeneral time = new TimeGeneral();
    ScheduleData scheduleAdd = new ScheduleData()
            .withId("scheduleSingleCancel")
            .withVer(0)
            .withFromDate(time.time(period))
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.time(period))
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list).withCancelled(true)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1").withOneTime(true);

    for (ScheduleData scheduleBefore : before) { //найти в списке "до" родителя с таким id
      if (scheduleBefore.getId().equals("scheduleSingleCancel")) {
        Schedules aaa = before.without(scheduleBefore).withAdded(scheduleAdd);
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }


}
