package tests.screenShot;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
  сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
  со свойством -Detalon=true.
*/

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotPayByAdminShop extends TestBase {

  private ImageDiff getDiff(String name, Set<By> locatorIgnor) throws AWTException, IOException {
    return app.sshot()
            .getImageDiff(
                    ApplicationManager.properties.getProperty("expected"),
                    ApplicationManager.properties.getProperty("actual"),
                    ApplicationManager.properties.getProperty("markedImages"),
                    name,
            locatorIgnor,
            1.25f);
  }

  @Test
  public void testSshotPayByAdminShop() throws AWTException, IOException {
    String name = "Admin_PayShop_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    app.payment().paymentAdminShop("21");
    app.sshot().changeTopBar();

    ImageDiff diff = this.getDiff(name, locatorIgnor);
    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

    Assert.assertEquals(diff.getDiffSize(), 0);

    app.payment().goToFamily("paymentAdmin");
  }
}
