package io.itgen.tests.schedule;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.schedule.Schedules;
import io.itgen.services.ScheduleService;
import io.itgen.tests.TestBase;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ScheduleRegularCreationTests extends TestBase {
  //Тест на создание постоянного расписания на текущую дату при выборе дефолтного тренера
  String idSchedule;

  @Test
  public void testScheduleRegularCreation() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().createRegularSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size() + 1));//что список в бд увеличился на 1
    idSchedule = app.schedule().getIdNewScheduleDB(before, after);
    MatcherAssert.assertThat(app.dbschedules().findByIdList(idSchedule).size(), equalTo(1));
    app.goTo().menuTasks();// что расписание только 1
    //может быть можно как то спрогнозировать создаваемое расписание, но там важен момент времени, что
    //влечет усложнение кода проверок
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById(idSchedule);
  }

}
