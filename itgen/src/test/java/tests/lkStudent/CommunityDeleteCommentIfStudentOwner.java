package tests.lkStudent;
/* T-461
 * Студент подписчик на сообщетов Скрейч и есть коммент от студента. Удалить свой коммент. */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunityDeleteCommentIfStudentOwner extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community()
        .set3_CommunityWithPostAndCommentFromStudent("21", "Очень интересно", "Scratch");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunityDeleteCommentIfStudentOwner() throws InterruptedException {
    app.lkStudent().btnCommunities();
    app.lkStudent().selectTitleCommunity();
    app.lkStudent().btnPoint();
    app.lkStudent().btnDeletePost();
    Thread.sleep(2000);
    app.check().notFindElement(app.lkStudent().getTextComment());
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
