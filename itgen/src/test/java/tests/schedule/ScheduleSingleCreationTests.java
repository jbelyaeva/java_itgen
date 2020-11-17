package tests.schedule;
// Тест на создание разового расписания на текущую дату при выборе дефолтного тренера

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import data.services.ScheduleService;
import app.testbase.TestBase;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ScheduleSingleCreationTests extends TestBase {
  String idSchedule;

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleSingleCreation() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().createSingleSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size() + 1));
    idSchedule = app.schedule().getIdNewScheduleDB(before, after);
    MatcherAssert.assertThat(
        app.dbschedules().findByIdList(idSchedule).size(), equalTo(1)); // что расписание только 1
    // может быть можно как то спрогнозировать создаваемое расписание, но там важен момент времени,
    // что
    // влечет усложнение кода проверок
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById(idSchedule);
  }
}
