package tests.screenShotStudent;
/*Начальные данные: дефолтный ребенок в статусе Занимается , без истории, без подписок сделать
 * скриншоты главного окна
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

public class SshotLearningWithHistoryWithoutSubscribers extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.finishedLessonWithProject().set9_LessonYesterdayFinishedWithProject(period);
    data.defFamily().set21_DefaultStudent_finishLessonBy1Skil();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test
  public void testLearningWithSubscribersAndHistory() throws AWTException, IOException {
    app.student().btnCloseTutorial();
    String name = "Student_LearningWithHistoryWithoutSubscribers_RU_Chrome";
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
    data.clean().taskAndSchedule().material().finishedLesson();
  }
}
