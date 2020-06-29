package io.itgen.tests.screenShot;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
   сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
   со свойством -Detalon=true.
 */

import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.WorkerData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Emails;
import io.itgen.services.WorkerService;
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

public class SshotListWorkers extends TestBase {
  public WorkerData deletedWorker;

  @BeforeMethod
  public void ensurePreconditions() {
    WorkerService workerService = new WorkerService();
    deletedWorker = new WorkerData().withId("sshotListWorker").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("employee"))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(Collections.singletonList(new Emails().withAddress("julja83@list.ru").withVerified(true)));
    workerService.create(deletedWorker);
  }

  @Test
  public void testSshotListWorkers() throws AWTException, IOException {
    String name = "Admin_ListWorkers_RU_Chrome";
    String[] locatorIgnor = {
            "//tbody//tr//td[4]",
            "//tbody//tr//td[3]"
    };

    app.goTo().menuTasks();
    app.goTo().menuWorkers();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    WorkerService workerService = new WorkerService();
    workerService.findByIdAndDelete("sshotListWorker");
  }

}
