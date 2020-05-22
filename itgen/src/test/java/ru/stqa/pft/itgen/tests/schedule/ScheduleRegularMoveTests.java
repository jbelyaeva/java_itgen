package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет подвижку разового расписания

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleRegularMoveTests extends TestBase {

  String idSchedule;
  @Test
  public void testScheduleRegularMove() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.schedule().createRegularSchedule(); //заменить транзакцией в предусловии
    Schedules before = app.db().schedules();
    idSchedule = app.schedule().move();
    Schedules after = app.db().schedules();
    assertThat(after.size(), equalTo(before.size()));
    //проверка, что подвинулось занятие
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.schedule().showElement();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData scheduleClean = scheduleService.findById(idSchedule);
    if (scheduleClean != null) {
      scheduleService.delete(scheduleClean);
    }
  }
}
