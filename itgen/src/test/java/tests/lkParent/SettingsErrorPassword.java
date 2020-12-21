package tests.lkParent;
/* T-114 */
/*Перейти в настройки у дефолтного ребенка. При установлении пароля ввести не совпадающие пароли - ошибка
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class SettingsErrorPassword extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsErrorPassword() {
    app.lkParent().reset();
    app.lkParent().typeNotSimilarPasswordInSettings();
  }
}
