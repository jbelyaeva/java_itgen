package tests.schedule;
/* T-372
 * Есть постоянное пустое занятие на сегодня 21:00.
 * нажать многоточие / заблокировать / заархивировать
 * проверить, что занятие заархивировано
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArchivingRegularSchedule extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.schedules().set19_TodayRegularScheduleWithoutStudents(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testArchivingSingleSchedule() throws InterruptedException {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().archivingSingleSchedule("newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set35_ArchivedTodayRegularSchedule(period, "newSchedule");
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
