package io.itgen.tests.screenSort;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
   сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
   со свойством -Detalon=true.
 */

import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.LeadData;
import io.itgen.model.users.Contacts;
import io.itgen.services.LeadService;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;

public class SshotListTrainers extends TestBase {

 @Test
  public void testSshotListTrainers() throws AWTException, IOException {
    String name = "Admin_ListTrainers_RU_Chrome";
    String[] locatorIgnor = new String[2];
    locatorIgnor[0]="//tbody//tr//td[5]";
    locatorIgnor[1]="//tbody//tr//td[9]";

    app.goTo().menuTasks();
    app.goTo().menuTrainers();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.propertiesAshot.getProperty("expected")
            , ApplicationManager.propertiesAshot.getProperty("actual")
            , ApplicationManager.propertiesAshot.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

}
