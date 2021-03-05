package tests.lkParent;
/* T-11 */
/* Зайти на главную страницу ЛК и ,нажав на имя ученика, перейти в таб Настройки.*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class GeneralPageTransitionFromStudentToSettings extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTransitionFromStudentToSettings() {
    app.lkParent().resetWithoutLogo();
    app.lkParent().clickByNameFirstStudent();
    app.student().btnCloseTutorial();
    app.base().waitVisibilityOfElementLocated(5, app.lkParent().getLabelPersonalInformation());
    app.check().findElement(app.lkParent().getTabSettings());
    app.check()
        .textElement(app.lkParent().getLabelPersonalInformation(), "Персональная информация");
    app.check().textElement(app.lkParent().getLabelLoginAndPassword(), "Логин и пароль ученика");
    app.check().textElement(app.lkParent().getLabelHowToJoinInstruction(),
        "Как зайти в личный кабинет ученика:");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.lkParent().btnLogo();
  }
}
