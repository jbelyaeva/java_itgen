package tests.screenShot;

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
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

public class SshotMainTasks extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    String text = "Узнать почту у родителя";
    String text1 = "Проверить материалы";
    data.tasksManual().set3_newManualTaskOnSuperAdmin("1", text, "open", "21");
    data.tasksAuto().set1_newAutoTaskToday("2", text, "open", "21");
    data.tasksManual().set3_newManualTaskOnSuperAdmin("3", text, "open", "student");
    data.tasksManual().set3_newManualTaskOnSuperAdmin("4", text1, "open", "student");
    data.tasksManual().set7_newManualTaskOnTrainer("5", text1, "open", "student");
    data.tasksAuto().set2_AutoTaskYesterdayTake("6", "contactForPayment", "open", "21");
    data.tasksManual().set8_ManualTaskYesterdayDone("7", "Записать на пробное", "closed", "21");
    data.tasksManual()
        .set10_ManualTaskYesterdayWaitAnswer("8", "Записать на пробное", "wait", "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotMainTasks() throws AWTException, IOException {
    String name = "Admin_TasksMain_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//td[@class='dueDate']"));

    app.goTo().menuRequests();
    app.goTo().menuTasks();
    app.sshot().changeTopBar();

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
    data.clean().taskAndSchedule().student().family().parent();
  }
}
