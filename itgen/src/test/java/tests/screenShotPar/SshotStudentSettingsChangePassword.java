package tests.screenShotPar;
/*Т-113
 * Скриншот страницы настроек. Нажать на Изменить пароль
 */

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SshotStudentSettingsChangePassword extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotStudentSettingsChangePassword() throws AWTException, IOException {
    app.lkParent().reset();
    app.lkParent().clickByNameFirstStudent();
    app.student().btnCloseTutorial();
    app.lkParent().clickByChangePassword();
    app.sshot().changeTopBarInLKParent();

    String name = "Parent_SettingsChangePassword_RU_Chrome";
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
}
