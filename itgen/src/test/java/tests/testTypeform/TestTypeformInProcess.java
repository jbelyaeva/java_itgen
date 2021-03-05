package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformInProcess extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set7_TestInProcess();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformInProcess() throws InterruptedException {
    app.test().goToStudentProfileTabTests("21");
    // есть лейбл В процессе
    Thread.sleep(3000);
    assertThat(app.test().isElementPresent(By.xpath("//span[@class='in-process']")), equalTo(true));
    // есть кнопка Удалить и она не задизейблена
    app.check().onNotDisabled(By.xpath("//button[@id-qa='delete-test']"));
    // что можно скопировать ссылку (копируется весь текст)
    // app.test().checkHrefInProfileStudent(By.xpath("//div[@id-qa='copy-url']//input"));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().tests();
  }
}
