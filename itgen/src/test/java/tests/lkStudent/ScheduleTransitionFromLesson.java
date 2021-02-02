package tests.lkStudent;
/*T-342
 * Авторизоваться под дефолтным учеником. Есть запись на разовое на новое направление , которое началось.
 * Перейти в календарь расписания, кликнуть на это занятие
 * Проверить, что перекинуло в занятие
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleTransitionFromLesson extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set25_TodayStartSingleScheduleWithOneStudentOnNewSkill(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleTransitionFromLesson() {
    app.lkStudent().goToFeed();
    app.lkStudent().btnAllSchedule();
    app.lkStudent().selectLessonInSchedule();
    app.check().findElement(app.lkStudent().getBtnImReady());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule();
  }
}
