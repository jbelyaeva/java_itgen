package tests.screenShotPar;
/**
 * T-93 Ученик добавлен в дефолтную семью, он старше 7 лет, было пробное. Сделать скриншот
 * расписания (календарь)
 */

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SshotCalendarWithButtonRecord extends TestBase {

  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set12_LessonYesterdayFinished_StudentAddInDefaultFamily(period);
  }

  @Test
  public void testSshotCalendarWithButtonRecorde()
      throws AWTException, IOException, InterruptedException {
    app.lkParent().btnShowSchedule();
    String name = "Parent_CalendarWithBurronRecord_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='calendar']"));
    app.sshot().changeTopBar();
    app.lkParent().skipHelper();
    Thread.sleep(4000);
    ImageDiff diff =
            app.sshot()
                    .getImageDiff(
                            ApplicationManager.properties.getProperty("expected"),
                            ApplicationManager.properties.getProperty("actual"),
                            ApplicationManager.properties.getProperty("markedImages"),
                            name,
                            locatorIgnor,
                            1.25f);
    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
