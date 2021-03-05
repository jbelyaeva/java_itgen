package tests.screenShot;

import static app.appmanager.ApplicationManager.properties;

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

public class SshotPopupTasks extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tasksAuto().set1_newAutoTaskToday("task", "contactForPayment", "inProgress", "21");
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
    app.task().btnClosePopup();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
