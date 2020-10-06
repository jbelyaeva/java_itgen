package io.itgen.tests.testTypeform;

import io.itgen.services.TestResultsService;
import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDone extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[]{"1"};
    app.trTest().saveTest("InProcess", "Тест", "111111", "ru",
        "Test на переход на новое направление", 5, 5, 10, skills, createAt,
        null);

    testService.deleteField("InProcess", "removedAt");

    app.trTest().saveResultTest("InProcess", "21", "InProcess", "Тест",
        "111111", skills, "ru", 5, 5, createAt, "");
  }

  @Test
  public void testTypeformInProcess() throws InterruptedException {

  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }
}
