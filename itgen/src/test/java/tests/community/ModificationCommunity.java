package tests.community;
/* Кейс: есть созданное админом сообщество без тегов, изменить название и описание. Проверить, что сообщество
   корректно перезаписалось в бд , нет сообщений об ошибках */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.communities.Communities;
import data.model.communities.CommunityData;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModificationCommunity extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();

  @BeforeMethod
  public void ensurePreconditions() {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    app.trCommunity()
        .newCommunity(
            "modifyCommunity",
            new Date(),
            "666",
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            1,
            "Scratch",
            tags,
            "ru",
            skills);
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
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    app.trCommunity()
        .newCommunity(
            "modifyCommunity",
            new Date(),
            "666",
            community.getDescription().trim(),
            idManagers,
            idSubscUser,
            dateSubsc,
            1,
            community.getTitle(),
            tags,
            "ru",
            skills);
    CommunityData communityAdd = communitiesService.findByIdCommunity("modifyCommunity");

    for (CommunityData communityAfter : after) {
      if (communityAfter.getId().equals("modifyCommunity")) {
        assertThat(after, equalTo(after.without(communityAfter).withAdded(communityAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    communitiesService.dropCommTag();
  }
}
