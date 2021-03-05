package tests.schedule;
// автотест проверяет подвижку разового расписания

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleBadMoveTests extends TestBase {
  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.schedules().set18_SingleScheduleWithoutStudent(period, "14");
  }
 //Т-172
  @Test(retryAnalyzer = RunTestAgain.class) // нельзя подвинуть на время в прошлом
  public void testBadTimeScheduleSingleMove() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().badMove("newSchedule");
    Schedules after = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after.size(), before.size());
    app.goTo().menuTasks();
  }

  //Т-173
  @Test(alwaysRun = true)
  // нельзя подвинуть не поменяв дату и время
  public void testNoChangeDateTimeScheduleSingleMove() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().badMoveNotChangeDateTime("newSchedule");
    Schedules after = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after.size(), before.size());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
