package tests.lkStudent;
/* T-299
 * Студент подписчик на сообщетов Скрейч и  коммент от админа. Нельзя удалить/изменить этот коммент. */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunityDontEditAndDeleteCommentIfStudentNotOwner extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community().set10_CommunityWithPostAndCommentFromAdmin("Очень интересно", "Scratch");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunityDontEditAndDeleteCommentIfStudentNotOwner() {
    app.lkStudent().btnCommunities();
    app.lkStudent().selectTitleCommunity();
    app.check().notFindElement(app.lkStudent().getBtnPoint());
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
