package tests.lkStudent;
/*T-259
 *Выданный тест доступен к прохождению в ЛК ученика
 *В админке есть тест и он выдан дефолтному ученику.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformAvailableToStudent extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set7_TestInProcess();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformAvailableToStudent() {
    app.lkStudent().goToStudentProfileTabTests();
    app.check().elementAtributAvailable(By.xpath("//button[@id-qa='start']"));
    app.check().findElement(By.xpath("//span[@class='in-process']"));
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().tests();
  }
}
