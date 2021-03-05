package tests.community;
/* Кейс: есть созданное админом сообщество без тегов, изменить название и описание. Проверить, что сообщество
   корректно перезаписалось в бд , нет сообщений об ошибках */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.communities.Communities;
import data.model.communities.CommunityData;
import data.provides.LocaleUtilsTestData;
import data.services.CommunitiesService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModificationCommunity extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.community().set6_NewCommunity();
  }

  @Test(dataProvider = "validModifyCommunityFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testModificationCommunity(CommunityData community) {
    app.goTo().menuCommunities();
    Communities before = app.dbcommunity().communities();
    app.community().modifyCommunity(community);
    app.base().waitVisibilityOfElementLocated(5, By.xpath("//div[@class='title']"));
    app.check().textElement(By.xpath("//div[@class='title']"), community.getTitle());
    Communities after = app.dbcommunity().communities();
    assertThat(after.size(), equalTo(before.size()));
    check(after, community);
    app.goTo().menuTasks();
  }

  private void check(Communities after, CommunityData community) {
    //коллекция с тегами пустая, поэтому берем весь список новых тэгов
    String[] tags = (app.dbcommunity().tags());
    data.community()
        .set14_CommunityWithTags(tags, community.getDescription().trim(), community.getTitle());
    CommunityData communityAdd = communitiesService.findByIdCommunity("newCommunity");

    for (CommunityData communityAfter : after) {
      if (communityAfter.getId().equals("newCommunity")) {
        assertThat(after, equalTo(after.without(communityAfter).withAdded(communityAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
