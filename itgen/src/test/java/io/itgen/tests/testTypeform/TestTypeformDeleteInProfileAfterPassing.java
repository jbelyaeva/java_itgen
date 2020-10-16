package io.itgen.tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.services.TestResultsService;
import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeleteInProfileAfterPassing extends TestBase {

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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformDeleteInProfileAfterPassing() {
    app.test().goToStudentProfileTabTests("21");
    app.test().deleteTestInProfile();

    //проверить, что нет значка Pass
    assertThat(app.base().isElementPresent(By.xpath("//div[@class='success-icon']")),
        equalTo(false));

    //проверки, что кнопка удалить изменилась на Выдать
    assertThat(app.base().wd.findElement(By.xpath("//div[@class='title']//span")).getText(),
        equalTo("Тест"));
    assertThat(app.base().isElementPresent(By.xpath("//button[@id-qa='delete-test']")),
        equalTo(false));

    //проверка, что кнопка Выдать есть и она не задизейблена
    assertThat(app.test().buttonAtributAvailable(By.xpath("//button[@id-qa='give-test']")),
        equalTo(null));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }
}
