package tests.screenShotStudent;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class Sshot2LookHistory extends TestBase {

  @Test
  public void testSshot2LookHistory() throws AWTException, IOException {
    String name = "Student_History_RU_Chrome";
    app.student().goToHistory();

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='history-month-header']"));
    locatorIgnor.add(By.xpath("//div[@class='date']"));
    locatorIgnor.add(By.xpath("//div[@class='date today']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));
    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.student().closeHistory();
  }

}
