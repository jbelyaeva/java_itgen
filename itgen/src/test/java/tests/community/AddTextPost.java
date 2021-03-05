package tests.community;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, добавить текстовый пост*/

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

public class AddTextPost extends TestBase {

  CommunitiesPostData newPost = null;
  CommunitiesService communitiesService = new CommunitiesService();
  String text = "Ученик созванивается с преподавателем в своем "
      + "личном кабинете в оговоренное время и транслирует ему свой экран.";

  @BeforeMethod
  public void ensurePreconditions() {
    data.community().set6_NewCommunity();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testAddTextPost() throws InterruptedException {
    app.goTo().menuCommunities();
    Communities beforeComm = app.dbcommunity().communities();
    CommunitiesPosts beforePost = app.dbcommunity().posts();
    app.community().addTextPost(text);
    Thread.sleep(500); //не успевает прописаться в базу
    Communities afterComm = app.dbcommunity().communities();
    CommunitiesPosts afterPost = app.dbcommunity().posts();
    newPost = app.dbcommunity().lastPost();

    assertThat(afterComm.size(), equalTo(beforeComm.size()));
    assertThat(afterPost.size(), equalTo(beforePost.size() + 1));
    app.check().textElement(By.xpath("//div[@class='community-post-body']//span"), text);
    check(afterComm, afterPost);
    app.goTo().menuTasks();
  }

  private void check(Communities afterComm, CommunitiesPosts afterPost) {
    //проверим запись в коллекцию Communities (не изменилась)
    data.community().set6_NewCommunity();
    CommunityData communityAdd = communitiesService.findByIdCommunity("newCommunity");
    for (CommunityData communityAfter : afterComm) {
      if (communityAfter.getId().equals("newCommunity")) {
        assertThat(afterComm, equalTo(afterComm.without(communityAfter).withAdded(communityAdd)));
        return;
      }
    }
    //проверим запись в коллекцию CommunitiesPosts (добавилась)
    data.community().set6_NewPost(newPost.getId(), text);
    CommunitiesPostData communityPostAdd = communitiesService.findByIdCommPost(newPost.getId());

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
