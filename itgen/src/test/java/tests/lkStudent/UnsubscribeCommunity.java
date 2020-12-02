package tests.lkStudent;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов,
 * дефолтный студент подписан на сообщество. Отписаться от сообщества. Убедиться,
 * что кнопка изменилась с Отписаться на Подписаться */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UnsubscribeCommunity extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
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
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testUnsubscribeCommunity() {
    app.student().btnCommunities();
    app.student().unsubscribeOnCommunity();
    app.check().findElement(By.xpath("//button[@id-qa='subscribe']"));
    app.student().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
  }
}
