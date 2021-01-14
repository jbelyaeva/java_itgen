package tests.lkStudent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.TestResultsService;
import data.services.TestService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformAvailableToStudent extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[]{"1"};
    app.trTest()
        .saveTest(
            "test",
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

    testService.deleteField("InProcess", "removedAt");

    app.trTest()
        .saveResultTestInProcess(
            "InProcess", "21", "InProcess", "Тест", "111111", skills, "ru", 5, 5, createAt, "");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformAvailableToStudent() {
    app.lkStudent().goToStudentProfileTabTests();
    assertThat(
        app.test().elementAtributAvailable(By.xpath("//button[@id-qa='start']")),
        equalTo(null));
    assertThat(
        app.test().findLableInProcess(), equalTo(true));
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }
}
