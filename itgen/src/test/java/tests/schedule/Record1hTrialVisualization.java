package tests.schedule;
/* T-438
 * Есть новая семья с ребенком. Есть разовое пустое занятие на сегодня 21:00.
 * Админке направлений длительность пробного на скреч русский - 1 час
 * Перейти в занятие и открыть запись на занятие чрез поп-ап
 * Проверить:
 * Тип занятия по дефолту Пробное
 * В поле длительность доступны варианты Первый/Второй час.
 * Длительность 2 часа задизейблена
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Record1hTrialVisualization extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set1_FamilyAndSingleLesson(period);
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecord1hTrialVisualization() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    app.schedule().openRecordOnTrial(name, "newSchedule");
    app.check().onDisabled(app.schedule().getDuration2h());
    app.check().onNotDisabled(app.schedule().getSelectFirst1h());
    app.check().onNotDisabled(app.schedule().getSelectSecond1h());
    app.check().textElement(app.schedule().getTypeLessonTrialSelected(), "ДА");
    app.schedule().btnClosePopup();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 2);
  }
}
