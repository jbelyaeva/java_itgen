package tests.schedule;
/*T-385 Есть новая семья с бесплатным ребенком. Есть разовое занятие на сегодня 21:00. Записать черех поп-ап Запись на
 * занятие бесплатника на это занятие на платное (2ч)
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordFreeStudentOnSingleLesson extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set4_FamilyWithFreeStudentAndParent();
    data.schedules().se29_SingleScheduleWithoutStudent(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordFreeStudentOnSingleLesson() throws InterruptedException {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOn2h(name, "schedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set12_TodaySingleScheduleWithStudent(period, "schedule");
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.schedule().getStudentOnLessonInSchedule());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
