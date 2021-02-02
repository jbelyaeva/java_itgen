package tests.lkStudent;
/*t-274
 * Начальные данные: дефолтный ребенок в статусе Занимается , c историей, c подпиской.
 * Зайти в таб Лена и оставить комментарий под постом. Проверить, что в бд появилась запись о новом
 * комменте. В ui проверить, что счетчик комментов увеличился на 1, отображается комментарий под постом.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.CommunitiesPostComments;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FeedLeaveCommentForPost extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.community().set4_CommunityScratchWithPost_StudentSubscriber();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testFeedLeaveCommentForPost() {
    String comment = "Мой новый комментарий";
    CommunitiesPostComments before = app.dbcommunity().comments();
    app.lkStudent().addCommentInFeed(comment);
    app.check().textElement(app.lkStudent().getTextFirstPost(), comment);
    CommunitiesPostComments after = app.dbcommunity().comments();
    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    app.check().textElement(app.lkStudent().getCounterPosts(), "1");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().communities();
  }
}
