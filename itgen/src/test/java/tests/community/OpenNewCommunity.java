package tests.community;
/* Кейс: создано новое сообщестов (в ensurePreconditions()) без тегов, убедиться, что озданное сообщество отображается
 * в табе Управление и его (сообщество) можно открыть*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenNewCommunity extends TestBase {

  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
   data.community().set6_NewCommunity();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testOpenNewCommunity() {
    app.goTo().menuCommunities();
    app.community().btnAdministration();
    app.check().textElement(By.xpath("//h4[@id-qa='title']"), title);
    app.community().goInCommunity();
    app.check()
        .textElement(By.xpath("//div[@class='add-post-title']"), "Добавьте новую публикацию");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
