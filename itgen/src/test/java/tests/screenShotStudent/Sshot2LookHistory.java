package tests.screenShotStudent;

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

public class Sshot2LookHistory extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshot2LookHistory() throws AWTException, IOException {
    String name = "Student_History_RU_Chrome";
    app.student().refresh();
    app.student().btnCloseTutorial();
    app.student().goToHistory();
    app.sshot().changeTopBarInLKParent();

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='text']//span"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    String[] deleteElements = {"//span[@class='date']", "//div[@class='date today']",
        "//div[@class='date today']"};
    app.sshot().deleteElements(deleteElements);
    ImageDiff diff =
        app.sshot()
            .getImageDiffWithoutScroll(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.student().closeHistory();
    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
