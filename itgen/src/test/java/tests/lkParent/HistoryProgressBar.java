package tests.lkParent;
/*T-309
 *Есть Выполненные материалы по трем направлениям. Зайти в профиль ученика и проверить, что
 * 1. Есть кнопка Посмотреть Все
 * 2. После ее нажатия 3 прогресс-бара
 * 3. Кнопка Посмотреть все исчезла
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HistoryProgressBar extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set1_LessonYesterdayFinishedWithProject_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testHistoryProgressBar() {
    app.lkParent().reset();
    app.lkParent().clickByShowHistorySecondChild();
    app.check().countElements(app.lkStudent().getProgressBar(), 1);
    app.check().notFindElement(app.lkStudent().getBtnShowAllProgressBar());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().achievements().student().taskAndSchedule().finishedLesson();
  }
}
