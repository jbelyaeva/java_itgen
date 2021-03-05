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

public class SshotSignUpOnTrial extends TestBase {
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик
  // и разовое расписание на завтра в 18.00, на которое нужно записать добавленного ученика на
  // пробное
  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(period);
  }

  @Test
  public void testSignUpOnTrial() throws AWTException, IOException {
    app.lkParent().btnRecordOnTrail();
    app.lkParent().btnSelectSkill(1);
    app.lkParent().selectLesson();

    String name = "Parent_SignUpOnTrial_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//span[@class='day-of-month']"));
    locatorIgnor.add(By.xpath("//span[@class='month']"));
    locatorIgnor.add(By.xpath("//div[@class='time-zones']//div[contains(@class,'select')]//div"));
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
    app.lkParent().btnLogo();

    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
