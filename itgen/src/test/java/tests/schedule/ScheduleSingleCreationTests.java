package tests.schedule;
/*Т-182
 * Создать разовое расписание. Проверка , что перекидывает в расписание
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ScheduleSingleCreationTests extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleSingleCreation() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().createSingleSchedule();
    Schedules after = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    app.check().findElement(app.schedule().getBtnCreateSchedule());
    app.goTo().menuTasks(); // что расписание только 1
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule();
  }
}
