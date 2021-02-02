package tests.lkStudent;
/*T-294
/*Начальные данные: дефолтный ребенок c подпиской на сообщество, имеет право создвавать сообщество
 * Зайти в таб Лента сообщества и убедиться, что отображается пост. Есть табы Все, Лента, Управление */

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminCommunityTabFeed extends TestBase {

  private final String text = "Новый пост, новый пост";

  @BeforeMethod
  public void ensurePreconditions() {
    String title = "Scratch";
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().logoutByStudent();
    data.community().set3_CommunityWithPostAndComment("666", text, title);
    data.defFamily().set20_DefaultStudentAdminCommunity();
    app.base().refresh();
    app.base().goByHref(app.base().address() + "/login");
    app.lkStudent()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    ;
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testAdminCommunityTabFeed() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCommunities();
    app.lkStudent().tabFeedInCommunity();
    app.check().textElement(app.lkStudent().getPostCommunity(), text);
    app.check().findElement(app.lkStudent().getTabAdministrator());
    app.check().findElement(app.lkStudent().getTabFeed());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.defFamily().set19_ChangeDefaultStudentInStart();
    data.postClean().communities();
  }
}
