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
import data.services.CommunitiesService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateCommunityWithPicture extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
  CommunityData communityNew = null;

  @DataProvider
  public Iterator<Object[]> validCommunityFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/community_creation_picture.json")))) {
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

  @Test(dataProvider = "validCommunityFromJson", enabled = false)
  public void testCreateCommunityWithPicture(CommunityData community)
      throws IOException {
    app.goTo().menuCommunities();
    Communities before = app.dbcommunity().communities();
    String path = "/src/test/resources/testdata/file.jpg";
    app.community().addNewCommunityWithFile(community, path);
    Communities after = app.dbcommunity().communities();
    communityNew = app.dbcommunity().lastCommunity();
    assertThat(after.size(), equalTo(before.size() + 1));
    check(after, communityNew.getId(), community);
    app.goTo().menuTasks();
  }

  private void check(Communities after, String id, CommunityData community) {
    //коллекция с тегами пустая, поэтому берем весь список новых тэгов
    String[] tags = (app.dbcommunity().tags());
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    app.trCommunity()
        .newCommunity(
            communityNew.getId(),
            new Date(),
            "666",
            community.getDescription().trim(),
            idManagers,
            idSubscUser,
            dateSubsc,
            1,
            community.getTitle(),
            tags);
    CommunityData communityAdd = communitiesService.findByIdCommunity(id);

    for (CommunityData communityAfter : after) {
      if (communityAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(communityAfter).withAdded(communityAdd)));
        return;
      }
    }
    //т.к теги в новой записи и в старой не упорядочены, проверим их отдельно через множества
    String[] tagsNew = (app.dbcommunity().tags());
    String[] tagsOld = (communityNew.getTagIds().stream().toArray(n -> new String[n]));
    app.check().twoMas(tagsOld, tagsNew);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    communitiesService.dropCommTag();
  }
}
