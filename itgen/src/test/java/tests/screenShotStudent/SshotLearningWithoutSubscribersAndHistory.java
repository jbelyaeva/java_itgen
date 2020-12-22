package tests.screenShotStudent;
/*
 * Начальные данные: дефолтный ребенок в статусе Занимается , без истории, без подписок
 * сделать скриншоты главного окна
 */

import static app.appmanager.ApplicationManager.properties;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.services.StudentService;
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

public class SshotLearningWithoutSubscribersAndHistory extends TestBase {

  private final StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trStudent()
        .changeDefaultStudent(
            "21",
            "Ребенок",
            "Дефолтный",
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            "1",
            "+9875645311",
            2,
            "learning",
            1,
            "1",
            "1",
            1);
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test
  public void testLearningWithoutSubscribersAndHistory() throws AWTException, IOException {
    String name = "Student_LearningWithoutSubscribersAndHistory_RU_Chrome";
    app.sshot().maxBrowser();
    Set<By> locatorIgnor = new HashSet<>();
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
    app.trStudent()
        .changeDefaultStudent(
            "21",
            "Ребенок",
            "Дефолтный",
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            "1",
            "+9875645311",
            2,
            "noTrial",
            0,
            null,
            null,
            0);
    studentService.deleteField("21", "finishedLessonsCount");
    studentService.deleteField("21", "lastSubjs");
    studentService.deleteField("21", "usedSubjs");
    studentService.deleteField("21", "lessonCount");
  }
}
