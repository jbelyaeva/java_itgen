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
import java.util.Date;
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
    studentService.updateField("21", "roles", new String[]{"child", "createCommunities"});

    String[] tags = {};
    String[] idManagers = {"666", "21"};
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
  public void testDeleteManager() {
    app.community().deleteManager();
    app.base().logout();
    app.base().goByHref(app.base().address() + "/login");
    app.base().login("student", "111111");
    app.lkStudent().btnCommunities();
    app.lkStudent().goToFeed();
    app.check()
        .notFindElement(By.xpath("//button[@id-q='menu']"));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    app.student().logoutByStudent();
    app.base().goByHref(app.base().address() + "/login");
    app.base().login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }
}
