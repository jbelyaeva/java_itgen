package tests.lkStudent;
/* T-341 */
/*Открыть расписание и пролистать вперед на 1 месяц и назад. проверить, что месяц меняется
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleMonths extends TestBase {

  private final String period = "10:00 - 12:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set12_LessonYesterdayFinished_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleMonths() {
    app.lkStudent().goToFeed();
    app.lkStudent().btnAllSchedule();
    app.lkStudent().clickByRightPaginator();
    app.check().nextMonth(app.lkStudent().monthUI());
    app.lkStudent().clickByLeftPaginator();
    app.lkStudent().clickByLeftPaginator();
    app.check().previosMonth(app.lkStudent().monthUI());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
