package tests.lkParent;
/* T-117 */
/*есть ребенок в дефолтной семье с паролем и логином.
 * Перейти в настройки у этого ребенка. Начать менять логин, но затем нажать Отмена
 * 1.Проверить, что в бд не изменился username,
 * 2.что не изменился логин в ui
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsCancelLogin extends TestBase {

  private final String loginOld = "newUser";
  private final String loginNew = "loginNew";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsCancelLogin() {
    app.lkParent().reset();
    app.lkParent().cancelNewLogin(loginNew);
    app.check()
        .equalityOfTwoElements(data.studentService().findById("newStudent").getUsername(),
            loginOld);
    app.check()
        .textElement(app.lkParent().getLabelLoginInSettings(), loginOld);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student();
  }
}
