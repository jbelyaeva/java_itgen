package tests.scheduleWindow;
/* T-439
 * Есть новая семья с ребенком. Есть разовое пустое занятие на сегодня 21:00.
 * Админке направлений длительность пробного на скреч русский - 1 час
 * Перейти в окно записи
 * Записаться на первый час
 * Проверить занятие:
 * есть лейбл На пробное
 * запись на первый час занятия
 * запись на часовое пробное(урок длится 1 ч))
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowRecord1hTrialFirstHour extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set6_FamilyAndTomorrowSingleLesson(period);
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecord1hTrialFirstHour() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    app.windowSchedule().recordOn1hTrialOnFirstHour(name);
    app.goTo().menuSchedule();
    app.schedule().selectScheduleInListUIById("newSchedule");
    //проверяем что запись на пробное
    app.check().textElement(app.schedule().getLabelTrialOnLesson(), "Пробное");
    //проверяем, что запись на 1 час
    app.schedule().clickOnLessonWithChild();
    app.check().equalityOfTwoElements(app.schedule().getDuration(), 1);
    //проверяем, что запись на первый час
    app.check().equalityOfTwoElements(app.schedule().getDiffHoursStart(), 0);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 2);
  }
}
