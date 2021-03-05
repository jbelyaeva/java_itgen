package tests.screenShot;

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

public class SshotTypeformPassLookingResult extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set8_TestPassed();
  }

  @Test
  public void testTypeformPass() throws IOException, AWTException {
    String name = "Admin_TypeformLookingGoodResult_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();

    app.test().goToStudentProfileTabTests("21");
    app.test().checkHrefResults();

    String[] deleteElements = {"//span[@class='date']"};
    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    Assert.assertEquals(diff.getDiffSize(), 0);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().tests();
  }
}
