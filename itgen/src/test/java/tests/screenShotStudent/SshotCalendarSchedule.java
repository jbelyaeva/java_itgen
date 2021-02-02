package tests.screenShotStudent;
//T-338

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

public class SshotCalendarSchedule extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotCalendarSchedule() throws IOException, AWTException {
    app.lkStudent().goToFeed();
    app.lkStudent().btnAllSchedule();
    String name = "Student_Calendar_RU_Chrome";

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='calendar']"));
    locatorIgnor.add(By.xpath("//span[@class='user-time']"));
    locatorIgnor.add(By.xpath("//span[@class='text-capitalize']"));
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
