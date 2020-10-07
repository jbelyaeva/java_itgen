package io.itgen.tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.RunTestAgain;
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

public class SshotEntryOnTrial extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик
  // и разовое расписание на завтра в 18.00, на которое нужно записать добавленного ученика на
  // пробное
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "LKOnTrail", "14");

    app.trStudent()
        .StudentAddDefaultFamily("LKOnTrail", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testEntryOnTrial() throws AWTException, IOException, InterruptedException {
    app.lkParent().RecordOnTrail();

    String name = "Parent_EntryOnTrial_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='lesson']//span[1]"));
    locatorIgnor.add(By.xpath("//div[@class='day']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    app.sshot().changeTopBar();

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
    scheduleService.DeleteById("LKOnTrail");
    studentService.DeleteById("LKOnTrail");

    Tasks tasks = app.dbschedules().tasksComposition("LKOnTrail");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
