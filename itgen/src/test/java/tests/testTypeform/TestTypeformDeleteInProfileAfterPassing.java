package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeleteInProfileAfterPassing extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set8_TestPassed();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformDeleteInProfileAfterPassing() {
    app.test().goToStudentProfileTabTests("21");
    app.test().deleteTestInProfile();

    // проверить, что нет значка Pass
    assertThat(
        app.base().isElementPresent(By.xpath("//div[@class='success-icon']")), equalTo(false));

    // проверки, что кнопка удалить изменилась на Выдать
    assertThat(
        app.base().wd.findElement(By.xpath("//div[@class='title']//span")).getText(),
        equalTo("Тест"));
    assertThat(
        app.base().isElementPresent(By.xpath("//button[@id-qa='delete-test']")), equalTo(false));

    // проверка, что кнопка Выдать есть и она не задизейблена
    app.check().onNotDisabled(By.xpath("//button[@id-qa='give-test']"));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().tests();
  }
}
