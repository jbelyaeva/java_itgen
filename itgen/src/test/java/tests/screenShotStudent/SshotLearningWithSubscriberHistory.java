package tests.screenShotStudent;
/*
 * Начальные данные: дефолтный ребенок в статусе Занимается , c историей, c подпиской.
 * Сделать скриншоты главного окна
 */

import static app.appmanager.ApplicationManager.properties;

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

public class SshotLearningWithSubscriberHistory extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject().set9_LessonYesterdayFinishedWithProject(period);
    data.defFamily().set21_DefaultStudent_finishLessonBy1Skil();
    data.community().set4_CommunityScratchWithPost_StudentSubscriber("21");
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    app.student().btnCloseTutorial();
  }

  @Test
  public void testLearningWithoutSubscribersAndHistory() throws AWTException, IOException {
    String name = "Student_LearningWithSubscriberHistory_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='history-month-header']"));
    locatorIgnor.add(By.xpath("//div[@class='date']"));
    locatorIgnor.add(By.xpath("//div[@class='date today']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));
    app.sshot().changeTopBarInLKParent();
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
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.student().logoutByStudent();
    data.clean().finishedLesson().taskAndSchedule().material().communities();
    data.defFamily().set19_ChangeDefaultStudentInStart();
  }
}
