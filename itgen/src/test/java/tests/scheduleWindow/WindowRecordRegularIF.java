package tests.scheduleWindow;
/* T-157
 * Есть новая семья с ребенком. Есть регулярное ИФ пустое занятие на сегодня 18:00.
 * Перейти в окно записи
 * Записать нового ученика на постоянку в эту группу
 * Проверить
 * перекинуло в расписание ученика
 * занятие прописалось правильно в бд --> корректное отображение данных на занятии
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowRecordRegularIF extends TestBase {

  private final String period = "20:00 - 22:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set5_FamilyAndRegularIFLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecordRegularIF() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordOnIFRegular1h(name);
    app.check().findElement(app.schedule().getElementWithCalendar());
    app.goTo().menuSchedule();
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set36_TodayRegularScheduleWithStudentOnAllLessonsOnIF1h(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.schedule().selectScheduleInListUIById("newSchedule");
    app.check().findElement(app.schedule().getStudentOnLessonInSchedule());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
