package tests.schedule;
/* T-168 Есть новая семья с ребенком. Есть постоянное расписание у ученикана сегодня 21:00. Удалить с
 * сегодняшнего занятия ученика
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveStudentFromRegular2hSchedule extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithRegularLessons().set2_FamilyWithRegularLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRemoveStudentFromSingleSchedule() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().removeStudent("newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set17_TodayRegularScheduleWithoutStudentOnFirstLesson(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check()
        .textElement(app.lkParentRecord().getLabelWithoutStudents(), "Нет записанных учеников");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
