package tests.lkParent;
/* T-118 */
/*есть ребенок в дефолтной семье с паролем и логином.
 * Перейти в настройки у этого ребенка. Начать изменение пароля, но в конце нажать отменить.
 * Проверить, что в бд не изменился bcrypt
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsCancelPassword extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsCancelPassword() {
    String oldBcrypt = data.studentService().findById("newStudent").getServices().getBcrypt();
    app.lkParent().reset();
    app.lkParent().cancelPasswordInSettings();
    app.check()
        .equalityOfTwoElements(
            data.studentService().findById("newStudent").getServices().getBcrypt(), oldBcrypt);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student();
  }
}
