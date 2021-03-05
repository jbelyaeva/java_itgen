package tests.lkStudent;
/*T-81
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.
 * Нажать Перейти к занятию
 * Написать в чате (который на занятии) сообщение тренеру
 * Отредактировать это сообщение
 * Проверить: сообщение отображается в этом чате и в обычном чате под учеником, в
 * обычном чате под тренером
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonModifyMessage extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOnLessonModifyMessage() throws InterruptedException {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goOnLesson();
    app.lkStudent().btnCloseTutorialTips();
    app.base().refresh();
    app.lkStudent().sendMessageToTrainer("Привет");
    app.lkStudent().modifyMessage("Пока");
    Thread.sleep(3000);
    app.check().textElement(app.lkStudent().getAlongMessageInChatOnLesson(), "Пока");
    app.chat().btnOpenChat();
    app.check().textElement(app.lkStudent().getAlongMessageInChatPrewiev(), "Пока");
    String message = app.chat().getByTrainerMessageFromStudent("trainer", "111111");
    app.check().equalityOfTwoElements(message, "Пока");
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().chat();
  }
}
