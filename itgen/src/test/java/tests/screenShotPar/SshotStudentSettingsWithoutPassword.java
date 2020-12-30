package tests.screenShotPar;
/*Т-111
 * Скриншот страницы настроек. пароль не задан
 */

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SshotStudentSettingsWithoutPassword extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
    data.studentService().deleteField("newStudent", "services");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotStudentSettingsWithoutPassword() throws AWTException, IOException {
    app.lkParent().reset();
    app.lkParent().clickByNameSecondStudent();
    app.student().btnCloseTutorial();
    app.sshot().changeTopBarInLKParent();

    String name = "Parent_SettingsWithoutPassword_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student();
  }
}
