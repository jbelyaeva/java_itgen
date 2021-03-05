package tests.screenShot;
//T-325

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.model.general.Comments;
import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotPopupRequests extends TestBase {

  ArrayList<Comments> listcomment = new ArrayList<>();

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set3_requestOnScratch2hRegular();
    data.schedules().set2_RegularScheduleWithoutStudents(period);
  }

  @Test
  public void testSshotPopupRequests() throws AWTException, IOException, InterruptedException {
    String name = "Admin_PopupRequest_RU_Chrome";
    app.goTo().menuRequests();
    app.request().selectGreenRequest();
    String[] invisibleElements = {"//span[@class='user-time']"};

    app.sshot().invisibleElements(invisibleElements);
    Thread.sleep(3000);
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//span[@class='text-muted']"));
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
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family().parent().requests();
  }
}
