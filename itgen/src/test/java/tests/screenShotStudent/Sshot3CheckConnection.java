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

public class Sshot3CheckConnection extends TestBase {

  @Test
  public void testSshot3CheckConnections() throws AWTException, IOException {
    String name = "Student_CheckConnection_RU_Chrome";
    app.student().goToCheckConnection();
    app.student().deleteAlerts();

    Set<By> locatorIgnor = new HashSet<>();
    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.92f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

}
