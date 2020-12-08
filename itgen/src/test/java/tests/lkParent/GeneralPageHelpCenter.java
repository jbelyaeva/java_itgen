package tests.lkParent;
/* T-14 */
/* Перейти из главной страницы в Справочный центр
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class GeneralPageHelpCenter extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageHelpCenter() throws InterruptedException {
    app.lkParent().reset();
    //проверка внутри метода, что переход был и новая страница с нужным урл
    app.check().equalityOfTwoElements(app.lkParent().goToHelpCenter(), "https://itgen.io/help-ru");
    app.base().refresh();
  }
}
