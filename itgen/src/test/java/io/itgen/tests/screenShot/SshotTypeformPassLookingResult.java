package io.itgen.tests.screenShot;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.services.TestResultsService;
import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
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

public class SshotTypeformPassLookingResult extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[]{"1"};
    app.trTest().saveTest("Pass", "Тест", "111111", "ru",
        "Test на переход на новое направление", 5, 5, 10, skills, createAt,
        null);

    testService.deleteField("Pass", "removedAt");

    app.trTest().saveResultTest("TestPass", "21", "Pass", "Тест",
        "111111", skills, "ru", 5, 5, createAt, "", true);
  }

  @Test
  public void testTypeformPass() throws IOException, AWTException {
    String name = "Admin_TypeformLookingGoodResult_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();

    app.test().goToStudentProfileTabTests("21");
    app.test().checkHrefResults();

    String[] deleteElements = {
        "//span[@class='date']"};
    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    Assert.assertEquals(diff.getDiffSize(), 0);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }
}
