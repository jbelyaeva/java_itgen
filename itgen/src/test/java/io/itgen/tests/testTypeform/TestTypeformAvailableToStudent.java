package io.itgen.tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
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
    skills = new String[]{"1"};
    app.trTest().saveTest("newTest", "Тест", "111111", "ru",
        "Test на переход на новое направление", 5, 5, 10, skills, createAt,
        null);
    testService.deleteField("deleteTest", "removedAt");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformAvailableToStudent() {
    app.goTo().urlTests();
    app.test().goToStudentProfileTabTests("21");

    //проверка, что кнопка есть и она не задизейблена

    app.base().waiteVisibleElement(15, By.xpath("//button[@id-qa='give-test']"));
    assertThat(app.test().buttonAtributAvailable(By.xpath("//button[@id-qa='give-test']")),
        equalTo(null));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
