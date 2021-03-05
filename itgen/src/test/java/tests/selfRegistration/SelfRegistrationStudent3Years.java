package tests.selfRegistration;
/*Т-331 кейс:
1. Нужно добавить родителя лида под суперадмином
2. Взять его id
3. Вставить в ссылку на саморегистрацию
4. Заполнить форму - c  возрастом три года
5. Ошибка на форме
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.StudentData;
import data.provides.LocaleUtilsTestData;
import data.services.LeadService;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelfRegistrationStudent3Years extends TestBase {
  private String id = "selfRegistration3Year";

  LeadService leadService = new LeadService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.leads().set1_leadParent(id);
  }

  @Test(dataProvider = "validSelfRegistrationFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testSelfRegistrationStudent3Years(StudentData student) throws IOException {
    app.base().refresh();
    app.student().logout();
    app.lkParentRecord().goHref(id);
    app.lkParentRecord().selfRegistrationForStudent3Years(student);
    app.check().textElement(By.xpath("//p"), "Возраст ученика должен быть от 4 до 70 лет");
    app.base().goByHref(app.base().address() + "/login");
    app.base().login("superadmin", "111111");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    leadService.DeleteById(leadService.findById(id));
  }
}
