package tests.screenShotPar;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
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
        .studentAddDefaultFamilyAfterLesson(
            "paymantByGuest",
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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotPayByParentShopRU() throws AWTException, IOException {
    String name = "Parent_PayByParentShop_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.payment().goToShopByParent();
    app.sshot().changeTopBar();

    ImageDiff diff = this.getDiff(name, locatorIgnor);
    if (diff.getDiffSize() > 0) {
      diff = this.getDiff(name, locatorIgnor);
    }

    if (diff.getDiffSize() > 300) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
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
