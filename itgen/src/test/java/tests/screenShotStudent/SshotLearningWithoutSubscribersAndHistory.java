package tests.screenShotStudent;
/*
 * Начальные данные: дефолтный ребенок в статусе Занимается , без истории, без подписок
 * сделать скриншоты главного окна
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

public class SshotLearningWithoutSubscribersAndHistory extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set21_DefaultStudent_finishLessonBy1Skil();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test
  public void testLearningWithoutSubscribersAndHistory() throws AWTException, IOException {
    String name = "Student_LearningWithoutSubscribersAndHistory_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.sshot().changeTopBarInLKParent();
    String[] deleteElements = {"//div[contains(@class,'alert-success')]"};
    app.base().deleteElements(deleteElements);
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
    data.defFamily().set19_ChangeDefaultStudentInStart();
  }
}
