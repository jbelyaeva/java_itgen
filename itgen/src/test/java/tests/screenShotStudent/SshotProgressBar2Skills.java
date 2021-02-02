package tests.screenShotStudent;
/*Т304
 * у ученика завершены 2 проекта с Выполнен, есть 1 завершенный   урок по Скрейч, и по Манкрафт
 *  есть 1 ачивка
 * открыть профайл через быстрые переходы*/

import static app.appmanager.ApplicationManager.properties;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotProgressBar2Skills extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject().set6_ProgressBarFOrDefaultStudent_2ProjecsScratchMinecraft();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotProgressBar2Skills() throws IOException, AWTException {
    app.lkStudent().goInProfile();
    String name = "Student_ProgressBar2Skills_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//span[@class='experience']"));
    app.lkStudent().deleteAlerts();
    app.lkStudent().deleteAlerts();
    ImageDiff diff =
        app.sshot()
            .getImageDiffWithoutScroll(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.base().goByHref(app.base().address() + "/feed");
    int diffSize = diff.getDiffSize();
    if (diffSize > 200) { // погрешность
      Assert.assertEquals(diffSize, 0);
    }
  }
}
