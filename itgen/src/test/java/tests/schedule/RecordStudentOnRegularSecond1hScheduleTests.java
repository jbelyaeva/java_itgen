package tests.schedule;
/* T-152 Есть новая семья с ребенком. Есть постоянное пустое занятие на сегодня 21:00. Записать
 * через поп-ап Запись на занятие на это занятие на постоянку на второй час
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordStudentOnRegularSecond1hScheduleTests extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithRegularLessons().set1_FamilyAndRegularLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordStudentOnRegularSecond1h() throws InterruptedException {
    String name = "Маша Машина";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOnSecond1h(name, "newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set11_TodayRegularScheduleWithStudentOnAllLessonsOnSecond1h(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.schedule().getStudentOnLessonInSchedule());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().parent().taskAndSchedule();
  }
}
