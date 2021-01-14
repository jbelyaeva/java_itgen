package tests.schedule;
/*Т-171 Нельзя перенести занятие на время, на которое ученик уже записан
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleBadMoveOnOccupiedLessonTests extends TestBase {

  private final String periodMove = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.schedules().set12_TodaySingleScheduleWithStudent(period, "schedule");
    data.newFamilyWithSingleLessons().set2_FamilyAndSingleLesson(periodMove);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testBadMoveOnOccupiedLesson() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().badMoveOccupied(periodMove, "schedule");
    Schedules after = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after.size(), before.size());
    // проверка, что в списке недоступных студентов появился студент из предусловия

    app.check().findElement(app.schedule().getLabelBusyChild());
    app.check().onDisabled(app.schedule().getBtnMoveInPopup());
    app.base().refresh();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student().taskAndSchedule().family().parent();
  }
}
