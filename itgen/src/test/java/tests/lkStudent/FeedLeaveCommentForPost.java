package tests.lkStudent;
/**
 * Начальные данные: дефолтный ребенок в статусе Занимается , c историей, c подпиской.
 * Зайти в таб Лена и оставить комментарий под постом. Проверить, что в бд появилась запись о новом
 * комменте. В ui проверить, что счетчик комментов увеличился на 1, отображается комментарий под постом.
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.CommunitiesPostComments;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FeedLeaveCommentForPost extends TestBase {

  private final CommunitiesService communitiesService = new CommunitiesService();
  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {

    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", "21"};
    Date[] dateSubsc = {new Date(), new Date()};
    String[] skills = {"1"};
    app.trCommunity()
        .newCommunity(
            "newCommunity",
            new Date(),
            "666",
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            title,
            tags,
            "ru",
            skills);

    String[] idLikes = {};
    String[] idAttachments = {};
    app.trCommunity()
        .newCommunityPost(
            "newCommunity",
            "Ученик созванивается с преподавателем",
            "newCommunity",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testFeedLeaveCommentForPost() {
    String comment = "Мой новый комментарий";
    CommunitiesPostComments before = app.dbcommunity().comments();
    app.student().addCommentInFeed(comment);
    app.base().waitVisibilityOfElementLocated(5, By.xpath("//span[@class='count']"));

    app.check().textElement(By.xpath("//span[@class='count']"), "1");
    app.check()
        .textElement(By.xpath("//div[@class='post-comments']//span[@class='multiline-text']"),
            comment);
    CommunitiesPostComments after = app.dbcommunity().comments();
    assertThat(after.size(), equalTo(before.size() + 1));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    communitiesService.dropCommPost();
    communitiesService.dropCommPostComment();
  }
}
