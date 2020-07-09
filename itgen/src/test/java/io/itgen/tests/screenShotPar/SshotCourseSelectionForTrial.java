package io.itgen.tests.screenShotPar;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
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

public class SshotCourseSelectionForTrial extends TestBase {
  TaskService taskService = new TaskService();
  StudentService studentService = new StudentService();

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик
  @BeforeMethod
  public void ensurePreconditions() {
    app.trStudent()
        .StudentAddDefaultFamily("LKOnTrail", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test
  public void testSshotCourseSelectionForTrial() throws AWTException, IOException {
    app.lkParent().btnRecordOnTrail();

    String name = "Parent_CourseSelectionForTrial_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    app.sshot().changeTopBar();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.findByIdAndDelete("LKOnTrail");

    Tasks tasks = app.dbschedules().tasksComposition("LKOnTrail");
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDeleteTask(taskClean.getId());
    }
  }
}
