package tests.screenShotStudent;
/*
 * Начальные данные: дефолтный ребенок в статусе Занимается , c историей, c подпиской.
 * Сделать скриншоты главного окна
 */

import static app.appmanager.ApplicationManager.properties;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.TimeGeneral;
import data.model.materials.MaterialData;
import data.services.CommentService;
import data.services.CommunitiesService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.MaterialBranchService;
import data.services.MaterialChildsService;
import data.services.MaterialService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotLearningWithSubscriberHistory extends TestBase {

  private final CommunitiesService communitiesService = new CommunitiesService();
  private final ScheduleService scheduleService = new ScheduleService();
  private final StudentService studentService = new StudentService();
  private final TaskService taskService = new TaskService();
  private final CommentService commentService = new CommentService();
  private final MaterialChildsService materialChildsService = new MaterialChildsService();
  private final MaterialBranchService materialBranchService = new MaterialBranchService();
  private final FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  private final FinishedLessonService finishedLessonService = new FinishedLessonService();
  private final MaterialService materialService = new MaterialService();
  private final TimeGeneral time = new TimeGeneral();
  private final String period = "18:00 - 20:00";
  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(period, "ScheduleYesterday", "14", "21", "1");

    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");

    app.trMaterial()
        .publishedMaterial(
            "MaterialOnLessonFirst",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    app.trMaterial()
        .publishedMaterial(
            "MaterialOnLessonSecond",
            "14",
            "Лабиринт",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    app.trMaterial()
        .materialsOnLesson(
            "01",
            "21",
            "MaterialOnLessonFirst",
            true,
            "notStarted",
            "14",
            "ScheduleYesterday",
            time.dateYesterday(),
            "markHw",
            true,
            false,
            true,
            null,
            null);

    app.trMaterial()
        .materialsOnLesson(
            "02",
            "21",
            "MaterialOnLessonSecond",
            false,
            "done",
            "14",
            "ScheduleYesterday",
            time.dateYesterday(),
            "changeStatus",
            true,
            null,
            null,
            "notStarted",
            "done");

    app.trFinishedLesson()
        .finishedChildLesson(
            "ScheduleYesterday1856921",
            "ScheduleYesterday",
            time.dateYesterday(),
            0,
            "14",
            "21",
            "finished",
            0,
            3,
            "1",
            "ru",
            4,
            true,
            false,
            time.Stime(period),
            time.Etime(period));

    app.trFinishedLesson()
        .finishedLessonWithOneStudent(
            "ScheduleYesterday18569",
            "ScheduleYesterday",
            time.dateYesterday(),
            0,
            "14",
            time.Stime(period),
            time.Etime(period),
            time.Stime(period),
            time.Etime(period),
            "21",
            "finished",
            0,
            3,
            "1",
            "ru",
            4,
            true,
            false);

    MaterialData hwMaterial = materialService.findById("MaterialOnLessonFirst");
    MaterialData doneMaterial = materialService.findById("MaterialOnLessonSecond");
    String[] hwMaterials = {
        hwMaterial.getTitle(), hwMaterial.getType(), hwMaterial.getMaterialLink(), "notStarted"
    };
    String[] doneMaterials = {
        doneMaterial.getTitle(), doneMaterial.getType(), doneMaterial.getMaterialLink(), "done"
    };
    Integer[] grades = {3, 2, 4, 2, 4, 4};
    String[] text = {"Ученик очень старался", "Ученик очень старался", "Телепортация"};
    app.trMaterial()
        .addComment(
            "1",
            "14",
            "21",
            "ScheduleYesterday",
            time.dateYesterday(),
            app.base().DateWithCorrectionDays(- 1),
            hwMaterials,
            doneMaterials,
            "Проект Головоломка",
            time.Etime(period),
            time.Stime(period),
            grades,
            "Проект Лаборатория",
            "1",
            "finished",
            text);

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
            "learning",
            1,
            "1",
            "1",
            1);

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
    app.student().refresh();
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    app.student().btnCloseTutorial();
  }

  @Test
  public void testLearningWithoutSubscribersAndHistory() throws AWTException, IOException {
    String name = "Student_LearningWithSubscriberHistory_RU_Chrome";
    app.sshot().maxBrowser();
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='history-month-header']"));
    locatorIgnor.add(By.xpath("//div[@class='date']"));
    locatorIgnor.add(By.xpath("//div[@class='date today']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));
    app.sshot().changeTopBarInLKParent();
    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                2.0f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.student().logoutByStudent();
    scheduleService.DeleteById("ScheduleYesterday");
    materialBranchService.drop();
    materialChildsService.drop();
    materialService.drop();
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    taskService.drop();
    commentService.drop();
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
    studentService.deleteField("21", "finishedLessonsCount");
    studentService.deleteField("21", "lastSubjs");
    studentService.deleteField("21", "usedSubjs");
    studentService.deleteField("21", "lessonCount");
    communitiesService.dropCommunity();
    communitiesService.dropCommPost();
  }
}
