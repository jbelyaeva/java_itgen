package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет блокировку постоянного расписания , одного и все

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

public class ScheduleRegularBlockTests extends TestBase {
  ArrayList<C> list = new ArrayList();
  String period="21:00 - 23:00";
  int week=604800000;
  String note="заблок";

  @BeforeMethod
  public void ensurePreconditions() {
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("scheduleRegularBlock")
            .withVer(0)
            .withFromDate(app.time().time(period))
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(app.time().time(period))
                    .withSt(new ST().withS(app.time().Stime(period)).withE(app.time().Etime(period)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week))
                    .withSt(new ST().withS(app.time().Stime(period+week)).withE(app.time().Etime(period+week)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*2))
                    .withSt(new ST().withS(app.time().Stime(period+week*2)).withE(app.time().Etime(period+week*2)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*3))
                    .withSt(new ST().withS(app.time().Stime(period+week*3)).withE(app.time().Etime(period+week*3)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*4))
                    .withSt(new ST().withS(app.time().Stime(period+week*4)).withE(app.time().Etime(period+week*4)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*5))
                    .withSt(new ST().withS(app.time().Stime(period+week*5)).withE(app.time().Etime(period+week*5)))
                    .withC(list)))
            .withTimes(new Times().withStart(app.time().start(period)).withEnd(app.time().finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);

  }


  @Test
  public void testScheduleRegularBlock() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().block("scheduleRegularBlock",note);
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before,after);
  }


  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("scheduleRegularBlock");
  }

  private void check(Schedules before, Schedules after) {
    ScheduleData scheduleAdd = new ScheduleData()
            .withId("scheduleRegularBlock")
            .withVer(0)
            .withFromDate(app.time().time(period))
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(app.time().time(period))
                    .withSt(new ST().withS(app.time().Stime(period)).withE(app.time().Etime(period)))
                    .withC(list).withBlocked(true).withBlockDesc(note),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week))
                    .withSt(new ST().withS(app.time().Stime(period+week)).withE(app.time().Etime(period+week)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*2))
                    .withSt(new ST().withS(app.time().Stime(period+week*2)).withE(app.time().Etime(period+week*2)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*3))
                    .withSt(new ST().withS(app.time().Stime(period+week*3)).withE(app.time().Etime(period+week*3)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*4))
                    .withSt(new ST().withS(app.time().Stime(period+week*4)).withE(app.time().Etime(period+week*4)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*5))
                    .withSt(new ST().withS(app.time().Stime(period+week*5)).withE(app.time().Etime(period+week*5)))
                    .withC(list)))
            .withTimes(new Times().withStart(app.time().start(period)).withEnd(app.time().finish(period)))
            .withSkypeId("1");

    for (ScheduleData scheduleBefore : before) { //найти в списке "до" родителя с таким id
      if (scheduleBefore.getId().equals("scheduleRegularBlock")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
