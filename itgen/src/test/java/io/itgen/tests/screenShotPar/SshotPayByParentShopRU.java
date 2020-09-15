package io.itgen.tests.screenShotPar;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
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

  private ImageDiff getDiff(String name, Set<By> locatorIgnor) throws AWTException, IOException {
    return app.sshot()
        .getImageDiff(
            ApplicationManager.properties.getProperty("expected"),
            ApplicationManager.properties.getProperty("actual"),
            ApplicationManager.properties.getProperty("markedImages"),
            name,
            locatorIgnor,
            1.25f);
  }

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

    ImageDiff diff = this.getDiff(name, locatorIgnor);
    if (diff.getDiffSize() > 0) {
      diff = this.getDiff(name, locatorIgnor);
    }

    Assert.assertEquals(diff.getDiffSize(), 0);
    app.payment().goToFamily("111");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("FinishedSchedule");
    studentService.DeleteById("paymantByGuest");

    Tasks tasks = app.dbschedules().tasksComposition("paymantByGuest");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
