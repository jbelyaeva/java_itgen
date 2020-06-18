package io.itgen.tests.screenSort;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
   сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
   со свойством -Detalon=true.
 */

import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.FamilyData;
import io.itgen.model.StudentData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class SshotListStudents extends TestBase {

  @Test
  public void testSshotListStudents() throws AWTException, IOException {
   String name = "Admin_ListStudents_RU_Chrome";
    String[] locatorIgnor = null;
    app.goTo().menuTasks();
    app.goTo().menuStudents();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.propertiesAshot.getProperty("expected")
            , ApplicationManager.propertiesAshot.getProperty("actual")
            , ApplicationManager.propertiesAshot.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }
}
