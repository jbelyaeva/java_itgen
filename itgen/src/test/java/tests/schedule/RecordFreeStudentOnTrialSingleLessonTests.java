package tests.schedule;
/*T-149 Есть новая семья с ребенком. Есть занятие на сегодня 21:00. Записать черех поп-ап Запись на
 * занятие бесплатника на это занятие на пробное (2ч)
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordFreeStudentOnTrialSingleLessonTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set1_FamilyAndSingleLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordFreeStudentOnTrialSingleLesson() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOnTrial(name, "newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set8_TodaySingleScheduleWithStudent(period);
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
