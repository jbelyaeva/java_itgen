package tests.testTypeform;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.TestService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformAvailableToStudent extends TestBase {

  private final TestService testService = new TestService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[] {"1"};
    app.trTest()
        .saveTest(
            "newTest",
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
    testService.deleteField("deleteTest", "removedAt");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformAvailableToStudent() throws InterruptedException {
    app.goTo().menuTests();
    app.test().goToStudentProfileTabTests("21");
    Thread.sleep(5000);
    // проверка, что кнопка есть и она не задизейблена
    app.check().onNotDisabled(By.xpath("//button[@id-qa='give-test']"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
