package tests.lkStudent;
/*T-302
 *Есть занятие Дефолтного тренера с дефолтным учеником (пробное), которое уже началось.
 *Проверить, что кнопка Все расписание нажимается и ведет на расписание
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentPageScheduleTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentPageSchedule() {
    app.base().refresh();
    app.lkStudent().goToFeed();
    app.check().assertTrue(app.lkStudent().goOnSchedule());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule();
  }
}
