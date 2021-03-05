package tests.screenShotTrainer;

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

public class SshotLessonEnvelopes extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   data.newFamilyOnLesson().set3_StudentBeforeLesson();
  }

  @Test
  public void testSshotLessonEnvelopes() throws AWTException, IOException {
    app.trainer().gotoSchedule();
    app.trainer().envelopes("schedule");

    String name = "Trainer_LessonEnvelopes_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    String[] deleteElements = {
      "(//div[@class='text-capitalize'])[2]",
      "//div[@class='text-muted']",
      "//div[@class='duration']"
    };

    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.98f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.trainer().refresh();
    app.trainer().gotoTask();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().family().student().taskAndSchedule();
  }
}
