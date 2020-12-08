package tests.lkStudent;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов,
 * дефолтный студент без подписки. Поставить лайк под постом. Убедиться,
 * что счетчик лайков увеличился с 0 на 1. В бд добавилась информация о лайке. */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.CommunitiesPostData;
import data.model.communities.CommunitiesPosts;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddLikeToPostCommunity extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
  String title = "Scratch";
  String text = "Ученик созванивается с преподавателем";

  @BeforeMethod
  public void ensurePreconditions() {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    app.trCommunity()
        .newCommunity(
            "AddLike",
            new Date(),
            "666",
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            1,
            title,
            tags,
            "ru",
            skills);

    String[] idLikes = {};
    String[] idAttachments = {};
    app.trCommunity()
        .newCommunityPost(
            "AddLike",
            text,
            "AddLike",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testAddLikeToPostCommunity() {
    app.student().btnCommunities();
    app.community().goInCommunity();
    app.student().addLike();
    CommunitiesPosts afterPost = app.dbcommunity().posts();
    app.check().textElement(By.xpath("//span[@class='likes-count liked']"), "1");
    app.student().goToFeed();
    check(afterPost);
  }

  private void check(CommunitiesPosts afterPost) {
    //проверим запись в коллекцию CommunitiesPosts (добавилась)
    String[] idLikes = {"21"};
    String[] idAttachments = {};
    app.trCommunity()
        .newCommunityPost(
            "AddLike",
            text,
            "AddLike",
            true,
            new Date(),
            idLikes,
            1,
            "666",
            idAttachments);
    CommunitiesPostData communityPostNew = communitiesService.findByIdCommPost("AddLike");

    for (CommunitiesPostData communityPostAfter : afterPost) {
      if (communityPostAfter.getId().equals("AddLike")) {
        assertThat(afterPost,
            equalTo(afterPost.without(communityPostAfter).withAdded(communityPostNew)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    communitiesService.dropCommPost();
  }
}
