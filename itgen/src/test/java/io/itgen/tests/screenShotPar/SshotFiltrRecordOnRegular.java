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

public class SshotFiltrRecordOnRegular extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkRecordOnRegularSchedule", "1");

    app.trStudent()
        .StudentAddDefaultFamily_AfterTrial(
            "LkRecordOnRegularSchedule", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test
  public void testFiltrRecordOnRegular() throws AWTException, IOException {
    app.lkParent().GoToFiltrRecordRegular();

    String name = "Parent_FiltrRecordOnRegular_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[@class='DayPickerInput']//input"));
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
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("FinishedSchedule");
    studentService.DeleteById("LkRecordOnRegularSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnRegularSchedule");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
