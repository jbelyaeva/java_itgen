package tests.schedule;
/* T-153
 * Есть новая семья с ребенком. Есть постоянное пустое занятие на сегодня 21:00.
 * Перейти в занятие и открыть запись на занятие через поп-ап
 * Записать нового ученика на занятие формата ИФ
 * проверить: у ученика появилось 1 ч постоянное занятие в бд
 * перекинуло в занятие
 * проверить что в календаре появился лейбл ИФ
 *
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordInIFOnRegularSchedule extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set5_FamilyAndRegularIFLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordInIFOnRegularSchedule() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordOnRegularIF(name, "newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().findElement(app.schedule().getStudentOnLessonInSchedule());
    app.schedule().goInCalendar(name);
    app.check().findElement(app.schedule().getLabelIFInCalendar());
    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set36_TodayRegularScheduleWithStudentOnAllLessonsOnIF1h(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);

    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
