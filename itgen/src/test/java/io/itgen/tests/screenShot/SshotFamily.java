package io.itgen.tests.screenShot;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

import io.itgen.appmanager.ApplicationManager;
import io.itgen.tests.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotFamily extends TestBase {

  @Test
  public void testSshotFamilies() throws AWTException, IOException {
    String name = "Admin_Family_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//span[@class='user-time']"));

    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("21");
    app.family().btnFamily();
    app.sshot().changeTopBar();

    ImageDiff diffFirst =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diffFirst.getDiffSize() > 0) {
      ImageDiff diffSecond =
          app.sshot()
              .getImageDiff(
                  ApplicationManager.properties.getProperty("expected"),
                  ApplicationManager.properties.getProperty("actual"),
                  ApplicationManager.properties.getProperty("markedImages"),
                  name,
                  locatorIgnor,
                  1.25f);
      Assert.assertEquals(diffSecond.getDiffSize(), 0);
    } else {
      Assert.assertEquals(diffFirst.getDiffSize(), 0);
    }
  }
}
