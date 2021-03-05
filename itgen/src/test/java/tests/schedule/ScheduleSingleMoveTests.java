package tests.schedule;
/*T-185
 * Подвинуть развое расписание. Проверка, что записей в бд стало на 1 больше и что в ui отображается время
 * перенесенного занятия
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleSingleMoveTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.schedules().set18_SingleScheduleWithoutStudent(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleSingleMove() {
    String periodMove = "23:00 - 01:00";
    app.goTo().menuSchedule();
    app.base().refresh();
    Schedules before = app.dbschedules().schedules();
    app.schedule().move(periodMove, "newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    app.check()
        .textElement(app.schedule().getLabelInformationAboutSchedule(),
            "23:00 - 01:00,\nНе начато");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
