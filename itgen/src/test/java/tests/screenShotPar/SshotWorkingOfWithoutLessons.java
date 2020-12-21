package tests.screenShotPar;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.services.StudentService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

/**
 * T-97 К дефолтной семье добавлен ученик старше 7 лет. Вчера было завершено пробное, а затем так же
 * * вчера им было пропущено занятие. Зайти в окно оработки и убедиться, что 1 .Отображается чп
 * ученика и направление 2."В течении недели нет свободных групп. Пожалуйста, свяжитесь с
 * Айтигеником!" если нет групп.
 */
public class SshotWorkingOfWithoutLessons extends TestBase {

  StudentService studentService = new StudentService();
  String periodFirst = "10:00 - 12:00";
  String periodSecond = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedTrialSchedule", "14", "newStudent", "1");

    app.trScheduleYesterday()
        .finishedLesson(
            periodSecond,
            "FinishedSchedule",
            "14",
            "newStudent",
            "1",
            3,
            "skipped",
            true,
            false,
            false,
            "ru",
            120
        );

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);
  }

  @Test(enabled = false)
  public void testSshotWorkingOfWithoutLessons() throws AWTException, IOException {
    app.lkParent().goInWorkingOff();

    String name = "Parent_FiltrRecordOnSingle_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[@class='gena-form-item'][3]//input"));
    locatorIgnor.add(By.xpath("//span[@class='month']"));
    locatorIgnor.add(By.xpath("//div[contains(@class,'btn-group')]"));
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

    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule();
    studentService.DeleteById("newStudent");
  }
}
