package ru.stqa.pft.itgen.tests.schedule;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleRegularCreationTests extends TestBase {
//Тест на создание постоянного расписания на текущую дату при выборе дефолтного тренера
  String idSchedule;
  @Test
  public void testScheduleRegularCreation() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Schedules before = app.db().schedules();
    app.schedule().createRegularSchedule();
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
