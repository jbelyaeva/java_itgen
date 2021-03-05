package tests.screenShotPar;

import app.appmanager.ApplicationManager;
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

public class SshotFiltrRecordOnRegular extends TestBase {

  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set9_LessonYesterdayFinished_RegularLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
            period);
  }

  @Test
  public void testFiltrRecordOnRegular() throws AWTException, IOException {
    app.lkParent().GoToFiltrRecordRegular();

    String name = "Parent_FiltrRecordOnRegular_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[@class='gena-form-item'][3]//input"));
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
    if (diff.getDiffSize() > 300) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
