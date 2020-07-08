package io.itgen.tests.screenShot;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
   сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
   со свойством -Detalon=true.
 */

import io.itgen.appmanager.ApplicationManager;
import io.itgen.tests.TestBase;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;

public class SshotListTrainers extends TestBase {

  @Test
  public void testSshotListTrainers() throws AWTException, IOException {
    String name = "Admin_ListTrainers_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//table//tr[9]//td[9]"));
    locatorIgnor.add(By.xpath("//tbody//tr//td[5]"));
    locatorIgnor.add(By.xpath("//tbody//tr//td[9]"));
    locatorIgnor.add(By.xpath("//tbody//tr//td[3]"));

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
