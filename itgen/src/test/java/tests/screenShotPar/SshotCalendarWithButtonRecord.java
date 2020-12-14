package tests.screenShotPar;
/**
 * T-93 Ученик добавлен в дефолтную семью, он старше 7 лет, было пробное. Сделать скриншот
 * расписания (календарь)
 */

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

public class SshotCalendarWithButtonRecord extends TestBase {

  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkRecordOnSingleSchedule", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "LkRecordOnSingleSchedule",
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

  @Test
  public void testSshotCalendarWithButtonRecorde() throws AWTException, IOException {
    app.lkParent().btnShowSchedule();
    app.base().maxBrowser();
    String name = "Parent_CalendarWithBurronRecord_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='calendar']"));

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.99f);
    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.postClean().dropTaskAndSchedule();
    studentService.DeleteById("LkRecordOnSingleSchedule");
  }
}
