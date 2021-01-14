package tests.lkStudent;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, зайти под студентом в таб Все и
 * подписаться на это сообщество. Убедиться, что кнопка изменилась с Подписаться на Отписаться*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscribeCommunity extends TestBase {

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
  public void testSubscribeCommunity() {
    app.lkStudent().btnCommunities();
    app.lkStudent().subscribeOnCommunity();
    app.check().findElement(By.xpath("//button[@id-qa='unsubscribe']"));
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
  }
}
