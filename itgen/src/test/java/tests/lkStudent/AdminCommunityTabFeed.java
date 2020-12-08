package tests.lkStudent;
/*Начальные данные: дефолтный ребенок c подпиской на сообщество, имеет право создвавать сообщество
 * Зайти в таб Лента сообщества и убедиться, что отображается пост. Есть табы Все, Лента, Управление */

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

public class AdminCommunityTabFeed extends TestBase {

  private final CommunitiesService communitiesService = new CommunitiesService();
  private final StudentService studentService = new StudentService();
  String title = "Scratch";
  String text = "Новый пост, новый пост";

  @BeforeMethod
  public void ensurePreconditions() {
    app.student().btnCloseTutorial();
    app.student().logoutByStudent();
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

    app.student().refresh();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));;
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testAdminCommunityTabFeed() {
    app.sshot().maxBrowser();
    app.student().btnCloseTutorial();
    app.student().btnCommunities();
    app.student().tabFeed();
    app.check()
        .textElement(By.xpath("//div[@class='post-comments']//span[@class='multiline-text']"),
            text);
    app.check()
        .findElement(By.xpath("//div[@role='tablist']//button[@id-qa='administration']"));
    app.check().findElement(By.xpath("//div[@role='tablist']//button[@id-qa='feed']"));
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
  }
}
