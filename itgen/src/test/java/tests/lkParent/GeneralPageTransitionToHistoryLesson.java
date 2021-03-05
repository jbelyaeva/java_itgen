package tests.lkParent;
/* T-12 */
/* В дефолтную семью добавлен ученик с завершенным вчера занятием с историей проектов.
 * Перейти в таб История и проверить, что в нем отобраэается завершенное занятие
 */

import static core.general.DateFormat.formatDD;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageTransitionToHistoryLesson extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set1_LessonYesterdayFinishedWithProject_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTransitionToHistoryLesson() {
    app.lkParent().reset();
    app.lkParent().clickByShowHistorySecondChild();

    app.check().findElement(app.lkParent().getTabHistory());
    app.check().textElement(app.lkParent().getLabelPeriodLessonInHistory(), period);
    String day = formatDD(app.base().DateWithCorrectionDays(-1));
    app.check().textElement(app.lkParent().getLabelDayAndMonthLessonInHistory(), day);
    app.check().textElement(app.lkParent().getLabelProjectDoneInHistory(), "Лабиринт");
    app.check().textElement(app.lkParent().getLabelProjectNotStartedInHistory(), "Жуки");
    app.check().findElement(app.lkParent().getLabelGradeAboutLessonInHistory());
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().material().finishedLesson().student();
  }
}
