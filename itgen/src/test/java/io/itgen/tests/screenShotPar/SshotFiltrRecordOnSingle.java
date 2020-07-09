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

public class SshotFiltrRecordOnSingle extends TestBase {
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
            period, "FinishedSchedule", "14", "LkRecordOnSingleSchedule", "1");

    app.trStudent()
        .StudentAddDefaultFamily_AfterTrial(
            "LkRecordOnSingleSchedule", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test
  public void testFiltrRecordOnSingle() throws AWTException, IOException {
    app.lkParent().GoToFiltrRecordSingle();

    String name = "Parent_FiltrRecordOnSingle_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[@class='DayPickerInput']//input"));
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
                locatorIgnor);
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.findByIdAndDelete("FinishedSchedule");
    studentService.findByIdAndDelete("LkRecordOnSingleSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnSingleSchedule");
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDeleteTask(taskClean.getId());
    }
  }
}
