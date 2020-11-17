package tests.screenShotPar;

import app.appmanager.ApplicationManager;
import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.StudentService;
import data.services.TaskService;
import app.testbase.TestBase;
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
        .NewStudent(
            "LKOnTrail",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotCourseSelectionForTrial() throws AWTException, IOException {
    app.lkParent().btnRecordOnTrail();

    String name = "Parent_CourseSelectionForTrial_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    app.sshot().changeTopBar();
    app.lkParent().clickByFullArea();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("LKOnTrail");

    Tasks tasks = app.dbschedules().tasksComposition("LKOnTrail");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
