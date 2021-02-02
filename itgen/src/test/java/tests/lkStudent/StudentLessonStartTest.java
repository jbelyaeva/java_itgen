package tests.lkStudent;
/*T-268
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.
 * Проверить: отрылся таб с настройками звука
 * Нажать Перейти к занятию : перебросило в занятие, отображается лейбл Пробное
 * Нажать Готов: у ученика исчезла кнопка
 * Проверить: под тренером стоит Готов
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentLessonStartTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentLessonStart() {
    app.check().textElement(By.xpath("//h2"), "Проверка звука и видео");
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goOnLesson();
    app.lkStudent().btnImReady();
    app.check().findElement(By.xpath("//div[@class='lesson-label trial']"));
    app.check().notFindElement(By.xpath("//div[contains(@class,'preview')]//button"));
    String message = app.lkStudent().getMessageTrainer();
    app.check().equalityOfTwoElements(message, "Принял! Идет подготовка к звонку \uD83D\uDE0E");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule();
  }
}
