package tests.lkParent;
/* T-43 */
/* Перейти из главной страницы в Справочный центр и затем в туториал по лк родителя
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class GeneralPageTutorialByLkParent extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTutorialByLkParent() {
    app.lkParent().reset();
    app.lkParent().goToTutorialByLkParent();
    app.check().findElement(app.lkParent().winTutorialByLkParent());
    app.base().refresh();
  }
}
