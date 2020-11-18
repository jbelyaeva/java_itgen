package tests.screenShotStudent;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.TimeGeneral;
import data.model.materials.MaterialData;
import data.services.MaterialService;
import data.services.TestResultsService;
import data.services.TestService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
/*Начальные данные: дефолтный ребенок
1. прошедший вчера пробное с двумя проектами
2. у него два теста (пройденный и заваленый)
3. занятие сегодня уже началось
Серия скриншотов на ученика имеет особенность. В первом тесте мы создаем тестовые данные, пробегаем по
вкладкам и делаем скриншоты, в последнем тесте чистим данные в бд. Причина: нужно менять данные в дефолтном
ученике после выполнения фикстуры -> при обновлении документа происходит выход из учетки и надо заново логиниться.*/

public class Sshot1Feed extends TestBase {

  MaterialService materialService = new MaterialService();
  private final TimeGeneral time = new TimeGeneral();
  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private String[] skills = null;
  private String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(period, "ScheduleYesterday", "14", "21", "1");

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
            app.base().DateWithCorrectionDays(-1),
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
            "trialFinished",
            1,
            "1",
            "1",
            1);

    skills = new String[] {"1"};
    Date createTest = app.base().DateWithCorrectionDays(-3);
    app.trTest()
        .saveTest(
            "Pass",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createTest,
            null);

    testService.deleteField("Pass", "removedAt");

    app.trTest()
        .saveResultTest(
            "TestPass", "21", "Pass", "Тест", "111111", skills, "ru", 5, 5, createTest, "", true);

    Date createTestTNew = app.base().DateWithCorrectionDays(-4);
    skills = new String[] {"2"};
    app.trTest()
        .saveTest(
            "NotPass",
            "Тест",
            "22222",
            "ru",
            "Test очень крутой",
            5,
            5,
            10,
            skills,
            createTestTNew,
            null);

    testService.deleteField("NotPass", "removedAt");
    testService.updateField("NotPass", "entityTestId", "99999");

    app.trTest()
        .saveResultTest(
            "TestNotPass",
            "21",
            "NotPass",
            "Тест",
            "22222",
            skills,
            "ru",
            5,
            5,
            createTestTNew,
            "",
            false);
    testResultsService.updateField("TestNotPass", "entityTestId", "99999");

    long alreadyRun = 7200000; // 2 часа идет занятие
    String periodLesson = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun, periodLesson, "ScheduleStart", "23", "21", "1", "ru");
  }

  @Test
  public void testSshot1Feed() throws AWTException, IOException {
    String name = "Student_FeedPage_RU_Chrome";
    app.student().skipHelper();
    app.student().goToFeed();
    app.sshot().changeTopBar();

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//span[@class='date']"));
    locatorIgnor.add(By.xpath("//div[@class='date today']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));
    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
