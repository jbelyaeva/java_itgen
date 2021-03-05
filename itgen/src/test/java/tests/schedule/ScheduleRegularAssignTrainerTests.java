package tests.schedule;
/*Т-175
 * Есть постоянное занятие. Назначить нового тренера (c id=18) на первое занятие
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleRegularAssignTrainerTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.schedules().set19_TodayRegularScheduleWithoutStudents(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleRegularAssignTrainer() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().assignTrainer("newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules()
        .set20_TodayRegularScheduleWithoutStudentsWithDifferentTrainers(period, "14", "18");
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
