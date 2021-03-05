package tests.lkStudent;
/* T-298
 * Студент подписчик на сообщетов Скрейч и есть коммент от студента. Изменить свой коммент. */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunityEditCommentIfStudentOwner extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community()
        .set3_CommunityWithPostAndCommentFromStudent("21", "Очень интересно", "Scratch");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunityEditCommentIfStudentOwner() {
    String text = "Новый коммент";
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCommunities();
    app.lkStudent().selectTitleCommunity();
    app.lkStudent().btnPoint();
    app.lkStudent().btnEditPost();
    app.lkStudent().modifyComment(text);
    app.check().textElement(app.lkStudent().getTextComment(), text);
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
