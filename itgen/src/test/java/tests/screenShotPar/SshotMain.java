package tests.screenShotPar;
/*Кейс: */

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotMain extends TestBase {

  private ImageDiff getDiff(String name, Set<By> locatorIgnor) throws AWTException, IOException {
    return app.sshot()
        .getImageDiff(
            ApplicationManager.properties.getProperty("expected"),
            ApplicationManager.properties.getProperty("actual"),
            ApplicationManager.properties.getProperty("markedImages"),
            name,
            locatorIgnor,
            1.25f);
  }

  @BeforeMethod
  public void ensurePreconditions() {
    data.familyService().updateFieldBoolean("111", "isTrialBonusOff", false);
  }

  @Test // упадет, если заускать через shift все тесты в подпапке.
  public void testSshotMain() throws AWTException, IOException {
    String name = "Parent_Main_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.lkParent().skipHelper();
    app.lkParent().btnClickHistory();
    app.sshot().changeTopBar();

    ImageDiff diff = this.getDiff(name, locatorIgnor);
    if (diff.getDiffSize() > 200) {
      diff = this.getDiff(name, locatorIgnor);
    }
    if (diff.getDiffSize() > 200) {
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
