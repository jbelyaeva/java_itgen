package tests.lkParent;
/* T-15 */
/* тестовая ситуация: есть дефолтная семья, у которой увеличен баланс +1
 * На главной странице родителя перейти в блок История балланса. Проверить, что он открывается и
 * есть запись об изменении баланса */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageBlockBallansHistory extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.payments().set1_addPaymentInDefaultFamily();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageBlockBallansHistory() {
    app.lkParent().reset();
    app.lkParent().goToBallansHistory();
    app.check().findElement(app.lkParent().getBtnBlockHistoryBallans());
    app.check()
        .textElement(app.lkParent().getBtnInfoAboutReasonBallans(), "Корректировка (100.00 $)");
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().payment();
  }
}
