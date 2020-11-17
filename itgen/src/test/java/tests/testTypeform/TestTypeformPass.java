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

public class TestTypeformPass extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[] {"1"};
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
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformPass() {
    app.test().goToStudentProfileTabTests("21");

    // проверить, что есть значек Pass
    app.base().waitVisibleElement(10, By.xpath("//div[@class='success-icon']"));

    // проверить, что результат зеленый
    app.base().waitVisibleElement(2, By.xpath("//span[@class='score success']"));

    // есть кнопка Удалить и она не задизейблена
    assertThat(
        app.test().elementAtributAvailable(By.xpath("//button[@id-qa='delete-test']")),
        equalTo(null));

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
