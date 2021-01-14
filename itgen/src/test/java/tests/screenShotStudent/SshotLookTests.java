package tests.screenShotStudent;

import static app.appmanager.ApplicationManager.properties;

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

public class SshotLookTests extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {

    skills = new String[]{"1"};
    Date createTest = app.base().DateWithCorrectionDays(- 3);
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
            createTest,
            null);

    testService.deleteField("Pass", "removedAt");

    app.trTest()
        .saveResultTest(
            "TestPass", "21", "Pass", "Тест", "111111", skills, "ru", 5, 5, createTest, "", true);

    Date createTestTNew = app.base().DateWithCorrectionDays(- 4);
    skills = new String[]{"2"};
    app.trTest()
        .saveTest(
            "NotPass",
            "Тест",
            "22222",
            "ru",
            "Test очень крутой",
            5,
            5,
            10,
            skills,
            createTestTNew,
            null);

    testService.deleteField("NotPass", "removedAt");
    testService.updateField("NotPass", "entityTestId", "99999");

    app.trTest()
        .saveResultTest(
            "TestNotPass",
            "21",
            "NotPass",
            "Тест",
            "22222",
            skills,
            "ru",
            5,
            5,
            createTestTNew,
            "",
            false);
    testResultsService.updateField("TestNotPass", "entityTestId", "99999");
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test
  public void testSshotLookTests() throws AWTException, IOException {
    String name = "Student_Tests_RU_Chrome";
    app.lkStudent().goToStudentProfileTabTests();
    String[] deleteElements = {"//div[contains(@class,'alert-success')]"};
    app.base().deleteElements(deleteElements);

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[contains(@class,'finished')]//span[@class='date']"));
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
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }

}
