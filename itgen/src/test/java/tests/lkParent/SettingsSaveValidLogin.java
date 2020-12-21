package tests.lkParent;
/* T-115 */
/*есть ребенок в дефолтной семье с паролем и логином.
 * Перейти в настройки у этого ребенка. Изменить логин и сохранить.
 * 1.Проверить, что в бд изменился username,
 * 2.что изменился логин в ui
 * 3.что можно авторизоваться с новым логином
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsSaveValidLogin extends TestBase {

  private final String loginNew = "loginChange";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsSaveValidLogin() {
    app.lkParent().reset();
    app.lkParent().saveNewLogin(loginNew);
    app.check()
        .equalityOfTwoElements(data.studentService().findById("newStudent").getUsername(),
            loginNew);
    app.check()
        .textElement(app.lkParent().getLabelLoginInSettings(), loginNew);
    //проверки на аворизацию
    app.base().logoutByParent();
    app.base().goByHref(app.base().address() + "/login");
    app.base().login(loginNew, "111111");
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
