package tests.lkStudent;
/* T-275
 * Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов,
 * дефолтный студент без подписки. Поставить лайк под постом. Убедиться,
 * что счетчик лайков увеличился с 0 на 1. В бд добавилась информация о лайке. */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.CommunitiesPosts;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddLikeToPostCommunity extends TestBase {

  private final String text = "Ученик созванивается с преподавателем";

  @BeforeMethod
  public void ensurePreconditions() {
    String title = "Scratch";
    data.community()
        .set1_NewCommunityScratchWithoutTagsWithPostWithoutLikes("666", title, text);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testAddLikeToPostCommunity() {
    app.lkStudent().btnCommunities();
    app.community().goInCommunity();
    app.lkStudent().addLike();
    CommunitiesPosts after = app.dbcommunity().posts();

    data.community().set2_PostWithLike("666", text);
    CommunitiesPosts afterNew = app.dbcommunity().posts();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().textElement(app.lkStudent().getCountLikes(), "1");
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().communities();
  }
}
