package tests.community;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, есть пост.
 * Изменить текст поста и убедиться, что текст изменился в ui и в бд
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.Communities;
import data.model.communities.CommunitiesPostData;
import data.model.communities.CommunitiesPosts;
import data.model.communities.CommunityData;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModificationTextPost extends TestBase {

  CommunitiesPostData newPost = null;
  CommunitiesService communitiesService = new CommunitiesService();
  String title = "Scratch";
  String text = "Ученик созванивается с преподавателем";
  String newText = "Новый текст";

  @BeforeMethod
  public void ensurePreconditions() {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    app.trCommunity()
        .newCommunity(
            "ModifyPost",
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
            "ModifyPost",
            text,
            "ModifyPost",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testModificationTextPost() {
    app.goTo().menuCommunities();
    Communities beforeComm = app.dbcommunity().communities();
    CommunitiesPosts beforePost = app.dbcommunity().posts();
    app.community().modifyTextPost(newText);
    Communities afterComm = app.dbcommunity().communities();
    CommunitiesPosts afterPost = app.dbcommunity().posts();

    assertThat(afterComm.size(), equalTo(beforeComm.size()));
    assertThat(afterPost.size(), equalTo(beforePost.size()));
    app.check().textElement(By.xpath("//div[@class='community-post-body']//span"), newText);
    check(afterComm, afterPost);
    app.goTo().menuTasks();
  }

  private void check(Communities afterComm, CommunitiesPosts afterPost) {
    //проверим запись в коллекцию Communities (не изменилась)
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    app.trCommunity()
        .newCommunity(
            "ModifyPost",
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
    CommunityData communityAdd = communitiesService.findByIdCommunity("ModifyPost");

    for (CommunityData communityAfter : afterComm) {
      if (communityAfter.getId().equals("ModifyPost")) {
        assertThat(afterComm, equalTo(afterComm.without(communityAfter).withAdded(communityAdd)));
        return;
      }
    }
    //проверим запись в коллекцию CommunitiesPosts (добавилась)
    String[] idLikes = {};
    String[] idAttachments = {};
    app.trCommunity()
        .newCommunityPost(
            "ModifyPost",
            newText,
            "ModifyPost",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);
    CommunitiesPostData communityPostAdd = communitiesService.findByIdCommPost("ModifyPost");

    for (CommunitiesPostData communityPostAfter : afterPost) {
      if (communityPostAfter.getId().equals(newPost.getId())) {
        assertThat(afterPost,
            equalTo(afterPost.without(communityPostAfter).withAdded(communityPostAdd)));
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
