package tests.lkStudent;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, убедиться, что озданное сообщество отображается
 * в табе Все и его (сообщество) можно открыть. Нет табов Лента и Управление */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.CommunitiesService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentOpenNewCommunity extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
    communitiesService.dropCommPostComment();
    communitiesService.dropCommPost();
    communitiesService.dropCommunity();
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
  public void testStudentOpenNewCommunity() {
    app.student().btnCommunities();
    app.check().textElement(By.xpath("//button[@id-qa='all']"), "Все");
    app.check().notFindElement(By.xpath("//button[@id-qa='administration']"));
    app.check().notFindElement(By.xpath("//div[@role='tablist']//button[@id-qa='feed']"));
    app.check().textElement(By.xpath("//h4[@id-qa='title']"), title);
    app.community().goInCommunity();
    app.check()
        .textElement(By.xpath("//div[@class='posts-not-exist-item']"), "Тут еще нет публикаций");
    app.student().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
  }
}
