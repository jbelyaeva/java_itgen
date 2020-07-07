package io.itgen.tests.screenShot;
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

public class SshotListLeads extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    LeadService leadService = new LeadService();
    LeadData lead =
        new LeadData()
            .withId("SshotLeads")
            .withFirstName("Лид")
            .withLastName("Лидов")
            .withRoles(Collections.singletonList("child"))
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withStatus("new");
    leadService.create(lead);
  }

  @Test
  public void testSshotListLeads() throws AWTException, IOException {
    String name = "Admin_ListLeads_RU_Chrome";
    String[] locatorIgnor = {
      "//span[@class='user-time']", "//span[@class='date']", "//span[@class='time']"
    };

    app.goTo().menuTasks();
    app.goTo().menuLeads();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    LeadService leadService = new LeadService();
    leadService.findByIdAndDelete("SshotLeads");
  }
}
