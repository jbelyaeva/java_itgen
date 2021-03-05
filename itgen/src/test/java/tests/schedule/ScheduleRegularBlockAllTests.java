package tests.schedule;
/*T-177
 * заблокировать все занятия в постоянном расписании (занятие сегодня)
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleRegularBlockAllTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.schedules().set19_TodayRegularScheduleWithoutStudents(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleRegularBlockAll() {
    String note = "заблокировать расписание";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().blockAll("newSchedule", note);
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set21_TodayRegularScheduleWithoutStudentsWithBlockedAll(period, note);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.schedule().getLabelBlockSchedule());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
