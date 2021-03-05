package tests.lkStudent;
/*T-296
 * Начальные данные: дефолтный ребенок c подпиской на сообщество, имеет права на создание сообщества,
 * и правом менеджера (сделать админом)
 * Перейти в Сообщества и проверить, что в табе Все сообщество пропало и появилось в табе Управление. Есть кнопки
 *  удалить/изменить пост
 */

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerCommunityTabAllWithoutCommunity extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.student().btnCloseTutorial();
    app.student().logoutByStudent();
    data.community().set5_CommunityScratchWithPostAndComment_StudentSubscriberAndManager("21");
    data.defFamily().set20_DefaultStudentAdminCommunity();
    app.lkStudent()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testManagerCommunityTabAllWithoutCommunity() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCommunities();
    //проверка, что в табе Все не отображается сообщество
    app.lkStudent().tabAll();
    app.check().notFindElement(app.lkStudent().getTitleCommunity());
    //проверить что сообщество отображается в табе Управление
    app.lkStudent().tabAdministration();
    app.check().findElement(app.lkStudent().getTitleCommunity());
    //проверить что есть кнопки изменить/удалить
    app.community().goInCommunity();
    app.community().btnPointPost();
    app.check().findElement(app.lkStudent().getBtnDeletePost());
    app.check().findElement(app.lkStudent().getBtnEditPost());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.defFamily().set19_ChangeDefaultStudentInStart();
    data.clean().communities();
    app.base().refresh();
    app.base().goByHref(app.base().address() + "/login");
    app.lkStudent()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }
}
