package tests.lkStudent;
/*T-82
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.
 * Нажать Перейти к занятию
 * Написать в чате (который на занятии) сообщение тренеру
 * Удалить это сообщение
 * Проверить: сообщения нет в этом чате и в обычном чате под учеником, в
 * обычном чате под тренером
 */

import app.testbase.TestBase;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonDeleteMessage extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test()
  public void testChatOnLessonDeleteMessage() throws InterruptedException {
    app.lkStudent().btnCloseTutorial();
    app.base().refresh();
    app.lkStudent().goOnLesson();
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCloseTutorialTips();
    app.lkStudent().sendMessageToTrainer("Привет");
    app.lkStudent().deleteMessage();
    Thread.sleep(3000);
    app.check().notFindElement(app.lkStudent().getAlongMessageInChatOnLesson());
    app.chat().btnOpenChat();
    app.check().fieldNotNull(app.dbchat().lastMessage().getRemovedAt());
    app.lkStudent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().chat();
    app.base().refresh();
  }
}
