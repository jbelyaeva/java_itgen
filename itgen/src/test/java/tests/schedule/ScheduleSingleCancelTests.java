package tests.schedule;
/*T-181
 *Есть разовое расписание на сегодня без учеников. Отменить занятие. Глубокая проверка по бд.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleSingleCancelTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.schedules().set18_SingleScheduleWithoutStudent(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleSingleCancel() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().cancel("newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set24_TodaySingleScheduleWithoutStudentsCanceled(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().textElement(app.schedule().getLabelCancelSchedule(), "Отменено");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
