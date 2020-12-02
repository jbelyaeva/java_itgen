package tests.lkParent;
/* T-44 */
/* Перейти из главной страницы в Справочный центр и затем в туториал по переключению на ученика
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class GeneralPageTutorialBySwitchToStudent extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTutorialBySwitchToStudent() {
    app.lkParent().reset();
    app.lkParent().goToTutorialBySwitchToStudent();
    app.check().findElement(app.lkParent().winTutorialBySwitchToStudent());
    app.base().refresh();
  }
}
