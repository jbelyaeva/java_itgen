package ru.stqa.pft.itgen.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.*;
import ru.stqa.pft.itgen.services.ScheduleService;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ScheduleCreationTests extends TestBase {
  String idSchedule;
  @Test
  public void testScheduleCreation() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Schedules before = app.db().schedules();
    app.schedule().createSchedule();
    Schedules after = app.db().schedules();
    assertThat(after.size(), equalTo(before.size() + 1));
    idSchedule = app.schedule().getIdNewScheduleDB(before, after);

    ScheduleData scheduleAdd =  new ScheduleData().withId(idSchedule);
    assertThat(after, equalTo(before.withAdded(scheduleAdd)));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData scheduleClean = scheduleService.findById(idSchedule);
    if (scheduleClean != null) {
      scheduleService.delete(scheduleClean);
    }
  }
}
