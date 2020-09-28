package io.itgen.tests.screenShotTrainer;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.TimeGeneral;
import io.itgen.services.FamilyService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
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

public class SshotLessonEnvelopes extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow());
    app.trScheduleToday()
        .SingleScheduleWithOneStudentOnTrail(period, "envelope", "23", "envelop", "1", "ru");

    app.trFamily().newFamily("envelop", false, "txc");

    app.trStudent()
        .newStudent(
            "envelop", "Маша", "Машина", "expert", "AL", "Europe/Minsk", 2, "ru", "ru", "envelop");
  }

  @Test
  public void testSshotLessonEnvelopes() throws AWTException, IOException {
    app.trainer().maxBrowser();
    app.trainer().gotoSchedule();
    app.trainer().envelopes("envelop");

    String name = "Trainer_LessonEnvelopes_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    String[] deleteElements = {
      "(//div[@class='text-capitalize'])[2]",
      "//div[@class='text-muted']",
      "//div[@class='duration']"
    };

    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.98f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.trainer().refresh();
    app.trainer().gotoTask();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.drop();
    studentService.DeleteById("envelop");
    familyService.DeleteById("envelop");
  }
}
