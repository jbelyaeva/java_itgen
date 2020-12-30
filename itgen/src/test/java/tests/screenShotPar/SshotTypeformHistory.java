package tests.screenShotPar;
//T-121
import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.services.TestResultsService;
import data.services.TestService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotTypeformHistory extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[]{"1"};
    app.trTest()
        .saveTest(
            "Pass",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);

    testService.deleteField("Pass", "removedAt");

    app.trTest()
        .saveResultTest(
            "TestPass", "21", "Pass", "Тест", "111111", skills, "ru", 5, 5, createAt, "", true);

    app.trTest()
        .saveTest(
            "NotPass",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);

    testService.deleteField("NotPass", "removedAt");

    app.trTest()
        .saveResultTest(
            "TestNotPass",
            "21",
            "NotPass",
            "Тест",
            "111111",
            skills,
            "ru",
            5,
            5,
            createAt,
            "",
            false);
  }

  @Test
  public void testSshotTypeformHistory() throws IOException, AWTException {
    app.test().goToStudentProfileTabHistory("21");

    String name = "Parent_TypeformHistory_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();

    String[] deleteElements = {
        "//div[@class='history-month-header']", "//div[@class='date']", "//span[@class='time']",
        "//div[@class='date today']"
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
                1.90f);

    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }
}
