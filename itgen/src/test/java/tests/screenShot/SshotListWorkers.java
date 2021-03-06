package tests.screenShot;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
  сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
  со свойством -Detalon=true.
*/

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.model.users.WorkerData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import data.services.WorkerService;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class SshotListWorkers extends TestBase {
  public WorkerData deletedWorker;

  @BeforeMethod
  public void ensurePreconditions() {
    WorkerService workerService = new WorkerService();
    deletedWorker =
            new WorkerData()
                    .withId("sshotListWorker")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("employee"))
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(
                Collections.singletonList(
                    new Emails().withAddress("julja83@list.ru").withVerified(true)));
    workerService.save(deletedWorker);
  }

  @Test
  public void testSshotListWorkers() throws AWTException, IOException {
    String name = "Admin_ListWorkers_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//tbody//tr//td[4]"));
    locatorIgnor.add(By.xpath("//tbody//tr//td[3]"));

    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    app.worker().waitForLoadHeader();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    WorkerService workerService = new WorkerService();
    workerService.DeleteById("sshotListWorker");
  }
}
