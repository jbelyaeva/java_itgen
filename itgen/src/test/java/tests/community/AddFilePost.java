package tests.community;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, добавить пост с файлом*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.Communities;
import data.model.communities.CommunitiesPosts;
import data.model.communities.CommunityData;
import data.services.CommunitiesService;
import java.io.IOException;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddFilePost extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
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
            1,
            title,
            tags,
            "ru",
            skills);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testAddFilePost() throws IOException, InterruptedException {
    app.goTo().menuCommunities();
    Communities beforeComm = app.dbcommunity().communities();
    CommunitiesPosts beforePost = app.dbcommunity().posts();
    String path = "/src/test/resources/testdata/file.jpg";
    app.community().addFilePost(path);
    Thread.sleep(4000); //не успевает прописаться в бд
    boolean getFilePost = app.community()
        .filePostGetTrainerFromAdmin("trainer", "111111", "file.jpg");
    Communities afterComm = app.dbcommunity().communities();
    CommunitiesPosts afterPost = app.dbcommunity().posts();

    assertThat(afterComm.size(), equalTo(beforeComm.size()));
    assertThat(afterPost.size(), equalTo(beforePost.size() + 1));
    assertThat(getFilePost, equalTo(true));
    check(afterComm);
    app.goTo().menuTasks();
  }

  private void check(Communities afterComm) {
    //проверим запись в коллекцию Communities (не изменилась)
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
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
            1,
            title,
            tags,
            "ru",
            skills);
    CommunityData communityAdd = communitiesService.findByIdCommunity("newCommunity");

    for (CommunityData communityAfter : afterComm) {
      if (communityAfter.getId().equals("newCommunity")) {
        assertThat(afterComm, equalTo(afterComm.without(communityAfter).withAdded(communityAdd)));
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
