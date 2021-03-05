package tests.screenShotPar;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
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

public class SshotPayByParentShopRU extends TestBase {
  String period = "18:00 - 20:00";

  private ImageDiff getDiff(String name, Set<By> locatorIgnor) throws AWTException, IOException {
    return app.sshot()
            .getImageDiff(
                    ApplicationManager.properties.getProperty("expected"),
                    ApplicationManager.properties.getProperty("actual"),
                    ApplicationManager.properties.getProperty("markedImages"),
            name,
            locatorIgnor,
            1.25f);
  }

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set12_LessonYesterdayFinished_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotPayByParentShopRU() throws AWTException, IOException {
    app.test().goToStudentProfileTabHistory("21");
    app.lkParent().btnLogo();
    String name = "Parent_PayByParentShop_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.payment().goToShopByParent();
    app.sshot().changeTopBar();

    ImageDiff diff = this.getDiff(name, locatorIgnor);
    if (diff.getDiffSize() > 0) {
      diff = this.getDiff(name, locatorIgnor);
    }

    if (diff.getDiffSize() > 300) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.payment().goToFamily("111");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
