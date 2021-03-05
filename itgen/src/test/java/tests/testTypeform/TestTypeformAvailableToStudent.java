package tests.testTypeform;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformAvailableToStudent extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set1_1_NewTestForRusScratch();
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
    data.clean().tests();
  }
}
