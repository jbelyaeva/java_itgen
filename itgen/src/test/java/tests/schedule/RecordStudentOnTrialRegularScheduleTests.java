package tests.schedule;
/* T-164 Есть новая семья с ребенком. Есть постянное пустое занятие на сегодня 21:00. Записать
 * через поп-ап Запись на пробное занятие (первое в постоянном расписании)
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordStudentOnTrialRegularScheduleTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithRegularLessons().set1_FamilyAndRegularLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordStudentOnTrialRegularSchedule() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOnTrial(name, "newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set15_TodayRegularScheduleWithStudentWithTrialOnFirstLesson(period);
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
