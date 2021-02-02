package tests.screenShotPar;
/*T-309 дубль
 *Есть Выполненные материалы по трем направлениям. Зайти в профиль ученика и проверить, отображается
 * прогресс бар
 */

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotHistoryProgressBar extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set1_LessonYesterdayFinishedWithProject_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotHistoryProgressBar() throws IOException, AWTException {
    app.lkParent().reset();
    app.lkParent().clickByShowHistorySecondChild();
    String name = "Parent_HistoryProgressBar_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    String[] deleteElements = {
        "//div[@class='date']",
        "//div[@class='history-month-header']"
    };

    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiffWithoutScroll(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().material().achievements().student().taskAndSchedule().finishedLesson();
  }
}
