package tests.lkParent;
/* T-8 */
/* тестовая ситуация: есть дефолтная семья, к которой добавлен ученик младше 7 лет
  На главной странице у ребенка есть только кнопка Управлением расписания */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageStudentYounger7years extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   data.defFamily().set3_addNewStudentYanger7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageStudentYounger7years() {
    app.lkParent().reset();
    app.check().findElement(app.lkParent().getShowScheduleSecondChild());
    app.check().findElement(app.lkParent().getShowHistorySecondChild());
    app.check().notFindElement(app.lkParent().getBtnTrialSecondChild());
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student();
  }
}
