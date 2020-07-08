package io.itgen.tests.screenShotPar;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.tests.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotMain extends TestBase {


  @Test // упадет, если заускать через shift все тесты в подпапке.
  public void testSshotMain() throws AWTException, IOException {
    String name = "Parent_Main_RU_Chrome";
    String[] locatorIgnor = {"//div[contains(@id,'MeteorToys')]"};

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor);

    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
