package tests.screenShotPar;
//T-121

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

public class SshotTypeformHistory extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set9_TestSetDefaultStudent();
  }

  @Test
  public void testSshotTypeformHistory() throws IOException, AWTException {
    app.test().goToStudentProfileTabHistory("21");

    String name = "Parent_TypeformHistory_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();

    String[] deleteElements = {
        "//div[@class='history-month-header']", "//div[@class='date']", "//span[@class='time']",
        "//div[@class='date today']"
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
                1.90f);

    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().tests();
    app.lkParent().btnLogo();
  }
}
