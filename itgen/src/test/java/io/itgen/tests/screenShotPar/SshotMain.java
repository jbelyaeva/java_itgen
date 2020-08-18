package io.itgen.tests.screenShotPar;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.tests.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotMain extends TestBase {

  @Test // упадет, если заускать через shift все тесты в подпапке.
  public void testSshotMain() throws AWTException, IOException {
    String name = "Parent_Main_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.lkParent().btnClickHistory();
    app.sshot().changeTopBar();

    ImageDiff diffFirst =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diffFirst.getDiffSize() > 0) {
      ImageDiff diffSecond =
          app.sshot()
              .getImageDiff(
                  ApplicationManager.properties.getProperty("expected"),
                  ApplicationManager.properties.getProperty("actual"),
                  ApplicationManager.properties.getProperty("markedImages"),
                  name,
                  locatorIgnor,
                  1.25f);
      Assert.assertEquals(diffSecond.getDiffSize(), 0);
    } else {
      Assert.assertEquals(diffFirst.getDiffSize(), 0);
    }
  }
}
