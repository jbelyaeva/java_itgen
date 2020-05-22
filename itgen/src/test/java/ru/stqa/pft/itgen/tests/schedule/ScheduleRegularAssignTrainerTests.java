package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет назначение другого тренера в постоянном расписании на одно занятие и на все

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleRegularAssignTrainerTests extends TestBase {

  String idSchedule;
  @Test
  public void testScheduleRegularAssignTrainer() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.schedule().createRegularSchedule();
    Schedules before = app.db().schedules();
    idSchedule = app.schedule().assignTrainer();
    Schedules after = app.db().schedules();
    assertThat(after.size(), equalTo(before.size()));
    //проверка, что назначен новый тренер
  }

  @Test
  public void testScheduleRegularAssignTrainerOnAll() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.schedule().createRegularSchedule();
    Schedules before = app.db().schedules();
    idSchedule = app.schedule().assignTrainerAll();
    Schedules after = app.db().schedules();
    assertThat(after.size(), equalTo(before.size()));
    //проверка, что назначен новый тренер на все занятия
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
