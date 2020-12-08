package tests.lkStudent;
/** Начальные данные: дефолтный ребенок c подпиской на сообщество.
 * Зайти в таб Лена и  убедится в отсутвии кнопки Меню с удалить/изменить
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FeedCantDeletePost extends TestBase {

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
  public void testFeedCantDeletePost() {
    app.sshot().maxBrowser();
    app.student().goToFeed();
    assertThat(app.student().isElementPresent(By.xpath("//button[@id-q='menu']")), equalTo(false));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    communitiesService.dropCommPost();
  }
}
