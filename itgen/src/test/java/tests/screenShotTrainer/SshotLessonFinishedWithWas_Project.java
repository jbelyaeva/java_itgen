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

public class SshotLessonFinishedWithWas_Project extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   data.newFamilyOnLesson().set1_StudentOnLessonWithMaterials("schedule");
  }

  @Test
  public void testSshotLessonFinishedWithWas_Project() throws AWTException, IOException {
    app.trainer().gotoSchedule();
    app.trainer().finishedLessonWithWas_giveProject("schedule", "Жуки", "Лабиринт");

    String name = "Trainer_FinishedLessonWithWasProject_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='history-month-header']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    String[] deleteElements = {
      "//div[@class='text-capitalize'][2]",
      "//div[@class='text-muted']",
      "//div[@class='date']",
      "//div[@class='duration']",
      "//div[@class='time']",
      "//span[@class='create-time']"
    };
    app.sshot().deleteElements(deleteElements);
    app.sshot().deleteAlerts();
    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.93f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.trainer().gotoTask();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().family().student().finishedLesson().material();
  }
}
