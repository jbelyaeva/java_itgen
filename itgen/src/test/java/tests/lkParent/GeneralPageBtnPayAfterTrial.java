package tests.lkParent;
/**
 * T-129 В дефолтную семью добавлен ученик, который вчера прошел пробное - кнопка Пополнить активна
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageBtnPayAfterTrial extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.defFamily()
        .set12_LessonYesterdayFinished_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageBtnPayAfterTrial() {
    app.lkParent().reset();
    app.check().onNotDisabled(app.payment().getBtnPay());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
