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
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModificationTextPost extends TestBase {

  CommunitiesPostData newPost = null;
  CommunitiesService communitiesService = new CommunitiesService();
  String newText = "Новый текст";

  @BeforeMethod
  public void ensurePreconditions() {
    data.community().set14_CommunityWithPost();
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
    app.base().refresh();
    app.check().textElement(By.xpath("//div[@class='community-post-body']//span"), newText);
    check(afterComm, afterPost);
    app.goTo().menuTasks();
  }

  private void check(Communities afterComm, CommunitiesPosts afterPost) {
    //проверим запись в коллекцию Communities (не изменилась)
    //супером создано новое сообщестов без тегов
    data.community().set6_NewCommunity();
    CommunityData communityAdd = communitiesService.findByIdCommunity("newCommunity");

    for (CommunityData communityAfter : afterComm) {
      if (communityAfter.getId().equals("newCommunity")) {
        assertThat(afterComm, equalTo(afterComm.without(communityAfter).withAdded(communityAdd)));
        return;
      }
    }

    data.community().set6_NewPost("newPost", newText);
    CommunitiesPostData communityPostAdd = communitiesService.findByIdCommPost("newPost");

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
    data.clean().communities();
  }
}
