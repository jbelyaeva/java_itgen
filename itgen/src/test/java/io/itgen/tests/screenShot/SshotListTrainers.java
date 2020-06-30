package io.itgen.tests.screenShot;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
   сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
   со свойством -Detalon=true.
 */

import io.itgen.appmanager.ApplicationManager;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;

public class SshotListTrainers extends TestBase {

  @Test
  public void testSshotListTrainers() throws AWTException, IOException {
    String name = "Admin_ListTrainers_RU_Chrome";
    String[] locatorIgnor = {
            "//table//tr[9]//td[9]",
            "//tbody//tr//td[5]",
            "//tbody//tr//td[9]",
            "//tbody//tr//td[3]"

    };

    app.goTo().menuTasks();
    app.goTo().menuTrainers();
    app.sshot().changeTopBar();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

}
