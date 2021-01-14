package tests.schedule;
/* T-154 Есть новая семья с ребенком. Есть разовое пустое занятие на сегодня 21:00. Записать
 * через поп-ап Запись на занятие на это занятие два часа
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordStudentOnSingle2hScheduleTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set1_FamilyAndSingleLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordStudentOnSingle2h() throws InterruptedException {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOn2h(name, "newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set12_TodaySingleScheduleWithStudent(period, "newSchedule");
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.schedule().getStudentOnLessonInSchedule());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student().taskAndSchedule().family().parent();
  }
}
