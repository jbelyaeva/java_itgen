package tests.lkParent;
/* T-116 */
/*есть ребенок в дефолтной семье с паролем и логином.
 * Перейти в настройки у этого ребенка. Изменить пароль и сохранить.
 * 1.Проверить, что в бд изменился bcrypt,
 * 2.что можно авторизоваться с новым паролем
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsSaveValidPassword extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
  }
  private final String newPassword = "123456";

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsSaveValidPassword() {
    String oldBcrypt = data.studentService().findById("newStudent").getServices().getBcrypt();
    app.lkParent().reset();
    app.lkParent().changePasswordInSettings("123456");
    app.check()
        .noEqualityOfTwoElements(
            data.studentService().findById("newStudent").getServices().getBcrypt(), oldBcrypt);
    //проверки на аворизацию
    app.base().logoutByParent();
    app.base().goByHref(app.base().address() + "/login");
    app.base().login("newUser", newPassword);
    app.check().findElement(app.lkParent().getWinTutorialsInSettings());
    app.base().btnCloseTutorial();
    app.base().logoutByStudent();
    app.base().login("parent", "111111");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student();
  }
}
