package tests.lkTrainer;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, убедиться, что озданное сообщество отображается
 * в табе Все и его (сообщество) можно открыть*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.CommunitiesService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerOpenNewCommunity extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community().set6_NewCommunity();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerOpenNewCommunity() {
    app.goTo().menuCommunities();
    app.check().textElement(By.xpath("//button[@id-qa='all']"), "Все");
    app.check().textElement(By.xpath("//h4[@id-qa='title']"), title);
    app.community().goInCommunity();
    app.check().textElement(By.xpath("//div[@class='posts-not-exist-item']"), "Тут еще нет публикаций");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
  }
}
