package tests.screenShotStudent;
/* Кейс: открыть профайл через быстрые переходы*/

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotOpenProfile extends TestBase {

  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    studentService.deleteField("21", "finishedLessonsCountBySkill");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotOpenProfile() throws IOException, AWTException {
    app.lkStudent().goInProfile();
    app.lkStudent().deleteAlerts();
    String name = "Student_Profile_RU_Chrome";

    Set<By> locatorIgnor = new HashSet<>();

    String[] deleteElements = {
        "//span[@class='user-time']",
        "(//span[@class='gena-text-muted'])[1]",
    };
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
    app.base().goByHref(app.base().address() + "/feed");
    int diffSize = diff.getDiffSize();
    if (diffSize > 200) { // погрешность
      Assert.assertEquals(diffSize, 0);
    }
  }

}
