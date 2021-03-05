package tests.lkStudent;
/*T-295
 * Начальные данные: дефолтный ребенок c подпиской на сообщество, имеет право на создание сообщества
 * Зайти в таб Лента сообщества и убедиться, что есть кнопки удалить и модифицировать пост
 */

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminCommunityTabFeedDeleteAndModifyPost extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String title = "Scratch";
    String text = "Новый пост, новый пост";
    app.base().refresh();
    data.community().set3_CommunityWithPostAndCommentFromStudent("666", text, title);
    data.defFamily().set20_DefaultStudentAdminCommunity();
    app.base().refresh();
    app.base().goByHref(app.base().address() + "/login");
    app.lkStudent()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testAdminCommunityTabFeedDeleteAndModifyPost() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCommunities();
    app.lkStudent().tabFeedInCommunity();
    app.check().findElement(app.lkStudent().getBtnPointsInCommunities());
    app.lkStudent().btnPoint();
    app.check().findElement(app.lkStudent().getBtnEditPost());
    app.check().findElement(app.lkStudent().getBtnDeletePost());
    app.base().refresh();
    app.lkStudent().logout();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
    data.defFamily().set19_ChangeDefaultStudentInStart();
    app.base().goByHref(app.base().address() + "/login");
    app.lkStudent()
        .login("student", "111111");
  }
}
