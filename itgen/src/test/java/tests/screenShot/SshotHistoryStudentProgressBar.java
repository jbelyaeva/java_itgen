package tests.screenShot;
/*T-308 - дубль?((
 *Есть Выполненные материалы по трем направлениям. Зайти в профиль ученика
 * 1. Есть кнопка Посмотреть Все
 * 2. После ее нажатия 3 прогресс-бара
 */

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
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

public class SshotHistoryStudentProgressBar extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject()
        .set7_ProgressBarFOrDefaultStudent_3ProjecsScratchMinecraftPython();
  }

  @Test
  public void testSshotHistoryStudentProgressBar() throws AWTException, IOException {
    String name = "Admin_HistoryStudentProgressBar_RU_Chrome";
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("21");
    app.student().tabHistory();
    app.lkStudent().btnShowAllInProgressBar();
    Set<By> locatorIgnor = new HashSet<>();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.defFamily().set19_ChangeDefaultStudentInStart();
    data.clean().material().achievements();
  }
}
