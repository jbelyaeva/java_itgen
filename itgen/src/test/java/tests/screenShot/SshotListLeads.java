package tests.screenShot;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
  сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
  со свойством -Detalon=true.
*/

import app.appmanager.ApplicationManager;
import data.model.lead.LeadData;
import data.model.usersGeneral.Contacts;
import data.services.LeadService;
import app.testbase.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

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
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//span[@class='user-time']"));
    locatorIgnor.add(By.xpath("//span[@class='date']"));
    locatorIgnor.add(By.xpath("//span[@class='time']"));

    app.goTo().menuTasks();
    app.goTo().menuLeads();
    app.lead().waitForLoadHeader();

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
    LeadService leadService = new LeadService();
    leadService.DeleteById("SshotLeads");
  }
}
