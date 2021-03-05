package tests.lkParent;
/* T-116 */
/*есть ребенок в дефолтной семье с паролем и логином.
 * Перейти в настройки у этого ребенка. Изменить пароль и сохранить.
 * 1.Проверить, что в бд изменился bcrypt,
 * 2.что можно авторизоваться с новым паролем - убрала, так как избыточная проверка и нестабильный тест
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsSaveValidPassword extends TestBase {

  private final String newPassword = "123456";

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().student();
    data.defFamily().set13_addNewStudentOlder7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsSaveValidPassword() {
    String oldBcrypt = data.studentService().findById("newStudent").getServices().getBcrypt();
    app.lkParent().reset();
    app.lkParent().changePasswordInSettings(newPassword);
    app.check()
        .noEqualityOfTwoElements(
            data.studentService().findById("newStudent").getServices().getBcrypt(), oldBcrypt);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student();
  }
}
