package io.itgen.tests.screenShotTrainer;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.TimeGeneral;
import io.itgen.services.FamilyService;
import io.itgen.services.FinishedChildLessonService;
import io.itgen.services.FinishedLessonService;
import io.itgen.services.PaymentService;
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

public class SshotLessonFinishedWithNotWas extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  PaymentService paymentService = new PaymentService();
  private final TimeGeneral time = new TimeGeneral();
  FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  FinishedLessonService finishedLessonService = new FinishedLessonService();
  private String period = "";
  private long alreadyRun = 7200000; // 2 часа идет занятие

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "finishLessonByTrainer",
            "23",
            "finishLessonByTrainer",
            "1",
            "ru");

    app.trFamily().newFamily("finishLessonByTrainer", false, "txc");

    app.trStudent()
        .newStudent(
            "finishLessonByTrainer",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "finishLessonByTrainer");
  }

  @Test
  public void testSshotLessonFinishedWithNotWas() throws AWTException, IOException {
    app.trainer().maxBrowser();
    app.trainer().gotoSchedule();
    app.trainer().finishedLessonWithNotWas("finishLessonByTrainer");

    String name = "Trainer_FinishedLessonWithNotWas_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='text-capitalize'][2]"));
    locatorIgnor.add(By.xpath("//div[@class='text-muted']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    String[] deleteElements = {
        "//div[@class='text-capitalize'][2]",
        "//div[@class='text-muted']",
        "//div[@class='date']",
        "//div[@class='duration']",
        "//div[@class='time']"
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
                1.93f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.trainer().gotoTask();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("finishLessonByTrainer");
    studentService.DeleteById("finishLessonByTrainer");
    familyService.DeleteById("finishLessonByTrainer");
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    paymentService.drop();
  }
}
