package tests.lkParent;
/* T-95 */
/*Открыть расписание и пролистать вперед на 1 месяц и назад. проверить, что месяц меняется
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleMonths extends TestBase {

  private final StudentService studentService = new StudentService();
  String periodFirst = "10:00 - 12:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedTrialSchedule", "14", "LkCancelRegularSchedule", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "LkCancelRegularSchedule",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);
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
    studentService.DeleteById("LkCancelRegularSchedule");
    app.postClean().dropTaskAndSchedule();
  }
}
