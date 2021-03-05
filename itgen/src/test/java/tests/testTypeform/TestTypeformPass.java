package tests.testTypeform;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformPass extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set8_TestPassed();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformPass() {
    app.test().goToStudentProfileTabTests("21");

    // проверить, что есть значек Pass
    app.base().waitVisibleElement(10, By.xpath("//div[@class='success-icon']"));

    // проверить, что результат зеленый
    app.base().waitVisibleElement(2, By.xpath("//span[@class='score success']"));

    // есть кнопка Удалить и она не задизейблена
    app.check().onNotDisabled(By.xpath("//button[@id-qa='delete-test']"));

    // проверить, что есть ссылка Посмотреть ответы и она кликабельна
    app.base().waitVisibleElement(2, By.xpath("//a[@class='answers']"));
    app.test().checkHrefResults();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().tests();
  }
}
