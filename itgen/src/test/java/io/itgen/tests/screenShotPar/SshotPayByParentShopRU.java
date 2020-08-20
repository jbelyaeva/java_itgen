package io.itgen.tests.screenShotPar;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
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

public class SshotPayByParentShopRU extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(period, "FinishedSchedule", "14", "paymantByGuest", "1");

    app.trStudent()
        .StudentAddDefaultFamily_AfterTrial(
            "paymantByGuest", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test
  public void testSshotPayByParentShopRU() throws AWTException, IOException {
    String name = "Parent_PayByParentShop_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.payment().goToShopByParent();
    app.sshot().changeTopBar();

    ImageDiff diffFirst =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diffFirst.getDiffSize() > 0) {
      ImageDiff diffSecond =
          app.sshot()
              .getImageDiff(
                  ApplicationManager.properties.getProperty("expected"),
                  ApplicationManager.properties.getProperty("actual"),
                  ApplicationManager.properties.getProperty("markedImages"),
                  name,
                  locatorIgnor,
                  1.25f);
      Assert.assertEquals(diffSecond.getDiffSize(), 0);
    } else {
      Assert.assertEquals(diffFirst.getDiffSize(), 0);
    }
    app.payment().goToFamily("111");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.findByIdAndDelete("FinishedSchedule");
    studentService.findByIdAndDelete("paymantByGuest");

    Tasks tasks = app.dbschedules().tasksComposition("paymantByGuest");
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDeleteTask(taskClean.getId());
    }
  }
}
