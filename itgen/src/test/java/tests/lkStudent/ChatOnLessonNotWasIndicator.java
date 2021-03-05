package tests.lkStudent;
/*T-78
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.Есть диалог:
 * тренер написал ученику сообщение
 * Нажать Перейти к занятию.
 * В обычном чате нет индикатора
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonNotWasIndicator extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
    data.chat().set2_DialogStudentTrainerOwner("Hello", "21", "23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOnLessonNotWasIndicator() throws InterruptedException {
    app.lkStudent().btnCloseTutorial();
    app.base().refresh();
    app.lkStudent().goOnLesson();
    Thread.sleep(1000);
    app.check().notFindElement(app.lkStudent().getIndicatorChat());
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().chat();
  }
}
