package tests.lkStudent;
/**
 * Начальные данные: дефолтный ребенок c подпиской на сообщество, имеет права на создание сообщества,
 * и правом менеджера (сделать админом)
 * Перейти в Сообщества и проверить, что в табе Все сообщество пропало и появилось в табе Управление. Есть кнопки
 *  удалить/изменить пост
 */

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

public class ManagerCommunityTabAllWithoutCommunity extends TestBase {

  private final CommunitiesService communitiesService = new CommunitiesService();
  private final StudentService studentService = new StudentService();
  String title = "Scratch";
  String text = "Новый пост, новый пост";

  @BeforeMethod
  public void ensurePreconditions() {
    app.student().btnCloseTutorial();
    app.student().logoutByStudent();
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
            "newPost",
            "Ученик созванивается с преподавателем",
            "newCommunity",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);

    String[] idPostCommentLikes = {};
    String[] idPostCommentAttachments = {};
    app.trCommunity()
        .newCommunityPostComment(
            "newComment",
            text,
            "newPost",
            "newCommunity",
            false,
            new Date(),
            new Date(),
            idPostCommentLikes,
            0,
            "21",
            idPostCommentAttachments);

    app.trStudent()
        .changeDefaultStudent(
            "21",
            "Ребенок",
            "Дефолтный",
            new String[]{"child", "createCommunities"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            "1",
            "+9875645311",
            2,
            "noTrial",
            0,
            null,
            null,
            0);

    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testManagerCommunityTabAllWithoutCommunity() {
    app.sshot().maxBrowser();
    app.student().btnCloseTutorial();
    app.student().btnCommunities();
    //проверка, что в табе Все не отображается сообщество
    app.student().tabAll();
    app.check()
        .notFindElement(By.xpath("//h4[@id-qa='title']"));
    //проверить что сообщество отображается в табе Управление
    app.student().tabAdministration();
    app.check()
        .findElement(By.xpath("//h4[@id-qa='title']"));
    //проверить что есть кнопки изменить/удалить
    app.community().goInCommunity();
    app.community().btnPointPost();
    app.check()
        .findElement(By.xpath("//li[@id-qa='menu-delete']"));
    app.check()
        .findElement(By.xpath("//li[@id-qa='menu-edit']"));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {

    app.trStudent()
        .changeDefaultStudent(
            "21",
            "Ребенок",
            "Дефолтный",
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            "1",
            "+9875645311",
            2,
            "noTrial",
            0,
            null,
            null,
            0);
    studentService.deleteField("21", "lastSubjs");
    studentService.deleteField("21", "usedSubjs");
    studentService.deleteField("21", "finishedLessonsCount");
    studentService.deleteField("21", "finishedLessonsCountBySkill");
    studentService.deleteField("21", "lastSeen");
    communitiesService.dropCommunity();
    communitiesService.dropCommPost();
    communitiesService.dropCommPostComment();
    app.student().refresh();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }
}
