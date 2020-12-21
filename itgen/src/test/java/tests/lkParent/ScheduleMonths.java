package tests.lkParent;
/* T-95 */
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
    app.lkParent().reset();
    app.lkParent().clickByRightPaginator();
    app.check().nextMonth(app.lkParent().monthUI());
    app.lkParent().clickByLeftPaginator();
    app.check().previosMonth(app.lkParent().monthUI());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
