package tests.community;
/* Кейс: есть сообщество с подписчиком c правом на создание сообщества + право администрировать.
Перейти в раздел Администраторы
Удалить юзера из администраторов
ОР - юзер не может редактировать/удалять посты */

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.CommunitiesService;
import data.services.StudentService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteManager extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
  StudentService studentService = new StudentService();
  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
    studentService.updateArrayField("21", "roles", new String[]{"child", "createCommunities"});
    data.community().set5_CommunityScratchWithPostAndComment_StudentSubscriberAndManager("21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testDeleteManager() {
    app.goTo().menuCommunities();
    app.community().deleteManager();
    app.base().logout();
    app.base().goByHref(app.base().address() + "/login");
    app.base().login("student", "111111");
    app.lkStudent().btnCommunities();
    app.lkStudent().goToFeed();
    app.check().notFindElement(By.xpath("//button[@id-q='menu']"));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    app.student().logoutByStudent();
    app.base().goByHref(app.base().address() + "/login");
    app.base().login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }
}
