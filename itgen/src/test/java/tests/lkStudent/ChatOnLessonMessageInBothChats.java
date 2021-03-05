package tests.lkStudent;
/*T-83
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.
 * Нажать Перейти к занятию
 * Написать в обычном чате сообщение тренеру
 * Проверить: сообщение отображается в чате на занятии
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonMessageInBothChats extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOnLessonMessageInBothChats() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goOnLesson();
    app.lkStudent().btnCloseTutorialTips();
    app.chat().sendMessage("Тренер", "Привет");
    app.check().textElement(app.lkStudent().getAlongMessageInChatOnLesson(), "Привет");
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().chat();
  }
}
