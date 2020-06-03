package ru.stqa.pft.itgen.tests.schedule;
//Тест на создание разового расписания на текущую дату при выборе дефолтного тренера

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ScheduleSingleCreationTests extends TestBase {
  String idSchedule;

  @Test
  public void testScheduleSingleCreation() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().createSingleSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size() + 1));
    idSchedule = app.schedule().getIdNewScheduleDB(before, after);
    assertThat(app.dbschedules().findByIdList(idSchedule).size(), equalTo(1));// что расписание только 1
    //может быть можно как то спрогнозировать создаваемое расписание, но там важен момент времени, что
    //влечет усложнение кода проверок
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete(idSchedule);
  }

}
