package tests.scheduleWindow;
/* T-437
 * Есть новая семья с ребенком. Есть разовое пустое занятие на сегодня 21:00.
 * Админке направлений длительность пробного на скреч русский - 1 час
 * Перейти в окно записи
 * Проверить:
 * Направление Скретч по дефолту
 * Поле Длительность задизейблено в значении 1 час
 * В блоке расписания отображается первый и второй час занятия пн 17:00
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowRecord1hTrialVisualization extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set6_FamilyAndTomorrowSingleLesson(period);
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecord1hTrialVisualization() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    app.windowSchedule().showWindowForStudentOn1hTrial(name);
    app.check().textElement(app.windowSchedule().getDurationDisabledInFilter(), "1 час");
    app.check().textElement(app.windowSchedule().getSkillInFilter(), "Scratch");
    app.check().countElements(app.windowSchedule().getGroupLesson(), 2);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 2);
  }
}
