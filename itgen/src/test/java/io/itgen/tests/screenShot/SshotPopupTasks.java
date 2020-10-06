package io.itgen.tests.screenShot;

import static io.itgen.appmanager.ApplicationManager.properties;

import io.itgen.model.tasks.TaskData;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotPopupTasks extends TestBase {

  private final TaskService taskService = new TaskService();
  private final Date createAt = new Date();
  private final Date duoDateWithTime = new Date();
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;


  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .saveAutoTask(
            "AutoTask",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTime,
            duoDateSort,
            "666",
            "21",
            "21",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newAutoTask_takeAutoTask");
  }

  @Test
  public void testSshotPopupTasks() throws AWTException, IOException {
    app.goTo().menuRequests();
    app.goTo().menuTasks();
    app.sshot().changeTopBar();
    app.task().goToPopup();
    String name = "Admin_TasksPopup_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//td[@class='dueDate']"));
    locatorIgnor.add(By.xpath("(//div[contains(@class,'editable')])[3]"));
    locatorIgnor.add(By.xpath("//div[contains(@class,'client-time')]"));
    locatorIgnor.add(By.xpath("//div[contains(@class,'task-lesson')]"));
    locatorIgnor.add(By.xpath("//span[@class='text-muted']"));

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                properties.getProperty("expected"),
                properties.getProperty("actual"),
                properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.DeleteById("AutoTask");
  }
}
