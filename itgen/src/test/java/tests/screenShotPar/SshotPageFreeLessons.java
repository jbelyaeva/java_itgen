package tests.screenShotPar;
/*Т-42
 * Скриншот страницы с информацией о бесплатных занятиях.
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
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotPageFreeLessons extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPageFreeLessons() throws AWTException, IOException {
    app.lkParent().clickByFreeLesson();
    app.base().maxBrowser();

    String name = "Parent_FreeLessons_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    //locatorIgnor.add(By.xpath("//div[@class='lesson']//span[1]"));

    app.sshot().changeTopBarInLKParent();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.95f);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
