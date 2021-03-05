package tests.lkStudent;
/*T-79
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.
 * Нажать Перейти к занятию
 * Написать в чате (который на занятии) сообщение тренеру
 * Проверить: сообщение отображается в этом чате и в обычном чате под учеником, в
 * обычном чате под тренером
 *  * по информации от разработчика: метод получения одинаковый для всех, следовательно проверкой, что не отображается
 * под тренером можно принебречь и проверить только руками один раз (+ есть такая проверка по отслылке файла в чате на уроке)
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonSendMessageTrainer extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOnLessonSendMessageTrainer() {
    app.lkStudent().btnLogo();
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goOnLesson();
    app.base().refresh();
    app.lkStudent().sendMessageToTrainer("Привет");
    app.check().textElement(app.lkStudent().getAlongMessageInChatOnLesson(), "Привет");
    app.chat().btnOpenChat();
    app.check().textElement(app.lkStudent().getAlongMessageInChatPrewiev(), "Привет");
    app.lkStudent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().chat();
  }
}
