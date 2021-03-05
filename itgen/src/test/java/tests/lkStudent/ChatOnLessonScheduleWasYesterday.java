package tests.lkStudent;
/*T-77
 * Авторизоваться под дефолтным учеником. Есть вчера завершенное пробное
 * Перейти в календарь расписания, кликнуть на это занятие
 * Проверить что на занятии нет чата
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonScheduleWasYesterday extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "21:00 - 23:00";
    data.schedules().set28_YesterdayStudentFinishedTrialLesson(period, "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOnLessonScheduleWasYesterday() {
    app.lkStudent().goToFeed();
    app.lkStudent().btnAllSchedule();
    app.lkStudent().selectLessonInSchedule();
    app.check().notFindElement(app.lkStudent().getChatOnLesson());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().chat();
  }
}
