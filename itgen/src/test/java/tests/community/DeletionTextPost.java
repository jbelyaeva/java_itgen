package tests.community;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, есть пост.
 * Удалить пост и убедиться что пост не отображаетс в ui, но в бд пост не удалился -
 * в документе появилось поле removedAt
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.Communities;
import data.model.communities.CommunitiesPosts;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeletionTextPost extends TestBase {

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
            "DeletePost",
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
            "DeletePost",
            text,
            "DeletePost",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testDeletionTextPost() {
    app.goTo().menuCommunities();
    Communities beforeComm = app.dbcommunity().communities();
    CommunitiesPosts beforePost = app.dbcommunity().posts();

    app.community().deleteTextPost();

    Communities afterComm = app.dbcommunity().communities();
    CommunitiesPosts afterPost = app.dbcommunity().posts();

    assertThat(afterComm.size(), equalTo(beforeComm.size()));
    assertThat(afterPost.size(), equalTo(beforePost.size()));
    assertThat(communitiesService.findByIdCommPost("DeletePost").getRemovedAt(), notNullValue());
    app.check()
        .textElement(By.xpath("(//div[contains(@class,'not-exist')]/*)[1]"),
            "Тут еще нет публикаций");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    communitiesService.dropCommPost();
  }
}
