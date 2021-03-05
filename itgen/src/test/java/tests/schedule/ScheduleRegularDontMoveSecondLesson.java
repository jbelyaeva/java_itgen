package tests.schedule;
/*T-375
 * Есть регулярное занятие. Выбрать следующее (через неделю) и убедиться, что его нельзя перенести
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleRegularDontMoveSecondLesson extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.schedules().set19_TodayRegularScheduleWithoutStudents(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleRegularDontMoveSecondLesson() {
    app.goTo().menuSchedule();
    app.schedule().restoreSecondRegularLesson("newSchedule");
    app.check().notFindElement(app.schedule().getSelectMove());
    app.base().refresh();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
