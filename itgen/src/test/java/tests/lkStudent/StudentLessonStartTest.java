package tests.lkStudent;
/*T-268
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.
 * Проверить: отрылся таб с настройками звука
 * Нажать Перейти к занятию : перебросило в занятие, отображается лейбл Пробное
 * Нажать Готов: у ученика исчезла кнопка
 * Проверить: под тренером стоит Готов
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.services.ScheduleService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentLessonStartTest extends TestBase {

  private final TimeGeneral time = new TimeGeneral();
  private final long alreadyRun = 7200000; // 2 часа идет занятие
  ScheduleService scheduleService = new ScheduleService();
  private String period = "";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "ScheduleFinishLessonByTrainer",
            "23",
            "21",
            "1",
            "ru");
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
    assertThat(message, equalTo("Принял! Идет подготовка к звонку \uD83D\uDE0E"));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("ScheduleFinishLessonByTrainer");
  }
}
