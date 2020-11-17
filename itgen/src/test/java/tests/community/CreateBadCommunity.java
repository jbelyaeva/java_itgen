package tests.community;
/* Кейс: создать сообщество под админом, заполнив форму валидными значеням. Проверить, что сообщество
   корректно записалось в бд, нет сообщений об ошибках, протестить форму валидными значениями*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.model.communities.Communities;
import data.model.communities.CommunityData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateBadCommunity extends TestBase {

  @DataProvider
  public Iterator<Object[]> noValidCommunityFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/community_creation_bad.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<CommunityData> community =
          gson.fromJson(json, new TypeToken<List<CommunityData>>() {
          }.getType());
      return community.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @Test(dataProvider = "noValidCommunityFromJson")
  public void testCreateBadCommunity(CommunityData community) {
    app.goTo().menuCommunities();
    Communities before = app.dbcommunity().communities();
    app.community().addNewBadCommunity(community);
    Communities after = app.dbcommunity().communities();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();
  }

}
