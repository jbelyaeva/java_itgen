package tests.community;
/* Кейс: создать сообщество не заполнив обязательные поля. Проверить, что появляются нужные алерты и
сообщество не создалось*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.communities.Communities;
import data.model.communities.CommunityData;
import org.testng.annotations.Test;

public class CreateBadCommunity extends TestBase {

  @Test(dataProvider = "noValidCommunityFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testCreateBadCommunity(CommunityData community) {
    app.goTo().menuCommunities();
    Communities before = app.dbcommunity().communities();
    app.community().addNewBadCommunity(community);
    Communities after = app.dbcommunity().communities();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();
  }
}
