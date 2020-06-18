package io.itgen.tests.screenShot;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
   сравнивает его с эталонным.
 */


import io.itgen.appmanager.ApplicationManager;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;

public class SshotFamily extends TestBase {


 @Test
  public void testSshotFamilies() throws AWTException, IOException {
   String name = "Admin_Family_RU_Chrome";
    String[] locatorIgnor = new String[1];
    locatorIgnor[0]="//span[@class='user-time']";
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("19");
    app.family().btnFamily();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }
}
