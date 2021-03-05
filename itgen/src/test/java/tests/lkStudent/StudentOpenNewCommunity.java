package tests.lkStudent;
/* T-262
 * Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, убедиться, что созданное сообщество отображается
 * в табе Все и его (сообщество) можно открыть. Нет табов Лента и Управление */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentOpenNewCommunity extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community().set6_NewCommunity();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentOpenNewCommunity() {
    String title = "Scratch";
    app.lkStudent().btnCommunities();
    app.check().textElement(app.lkStudent().getTabAll(), "Все");
    app.check().notFindElement(app.lkStudent().getTabAdministrator());
    app.check().notFindElement(app.lkStudent().getTabFeedInCommunity());
    app.check().textElement(app.lkStudent().getTitleCommunity(), title);
    app.community().goInCommunity();
    app.check().textElement(app.lkStudent().getLabelNotPosts(), "Тут еще нет публикаций");
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
