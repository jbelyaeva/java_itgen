package tests.lkStudent;
/*T-76
 * Авторизоваться под дефолтным учеником. Есть запись на разовое на новое направление , которое не начал тренер, но оно
 * уже началось о времени.
 * Перейти в календарь расписания, кликнуть на это занятие
 * Проверить что на занятии нет чата
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonScheduleNotStarted extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 3600000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set27_TodaySingleScheduleWithDefaulStudent(period, "newSchedule");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOnLessonScheduleNotStarted() {
    app.lkStudent().goToFeed();
    app.lkStudent().btnAllSchedule();
    app.lkStudent().selectLessonInSchedule();
    app.check().notFindElement(app.lkStudent().getChatOnLesson());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().chat();
  }
}
