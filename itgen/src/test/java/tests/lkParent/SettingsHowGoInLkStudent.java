package tests.lkParent;
/* T-120 */
/*Перейти в настройки у дефолтного ребенка.Нажать в блоке Как войти в кабинет ученика 1 и 2
 *  - появилась гифка. Свернуть гифку
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsHowGoInLkStudent extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsHowGoInLkStudent() {
    app.lkParent().reset();
    app.lkParent().clickByNameSecondStudent();
    app.lkParent().clickByFirstTutorial();
    app.check().findElement(app.lkParent().getImageInstructionGif());
    app.lkParent().clickByFirstTutorial();
    app.lkParent().clickBySecondTutorial();
    app.check().findElement(app.lkParent().getImageInstructionGif());
    app.lkParent().clickBySecondTutorial();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student();
  }
}
