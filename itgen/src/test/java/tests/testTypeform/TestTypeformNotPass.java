package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.services.TestResultsService;
import data.services.TestService;
import app.testbase.TestBase;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformNotPass extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[] {"1"};
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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformNotPass() throws InterruptedException {
    app.test().goToStudentProfileTabTests("21");

    Thread.sleep(5000);
    // проверить, что нет значка Pass
    assertThat(
        app.base().isElementPresent(By.xpath("//div[@class='success-icon']")), equalTo(false));
    // проверить, что результат красный
    app.base().waitVisibleElement(2, By.xpath("//span[@class='score failed']"));
    // есть кнопки Удалить нет
    assertThat(
        app.base().isElementPresent(By.xpath("//button[@id-qa='delete-test']")), equalTo(false));
    // проверить, что есть ссылка Посмотреть ответы и она кликабельна
    app.base().waitVisibleElement(2, By.xpath("//a[@class='answers']"));
    app.test().checkHrefResults();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }
}
