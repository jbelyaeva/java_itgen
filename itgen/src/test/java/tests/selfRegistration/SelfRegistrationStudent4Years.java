package tests.selfRegistration;
/*Т-332 кейс:
1. Нужно добавить родителя лида под суперадмином
2. Взять его id
3. Вставить в ссылку на саморегистрацию
4. Заполнить форму
7. Регистрация произошла - сообщение на последней странице
8. Затем вернуться в лк суперадмина
8. Провести проверку лида по бд (добавилось поле note)
 */

import static app.appmanager.HelperBase.DateISOToUsualDataString;
import static app.appmanager.HelperBase.DateWithCorrectionDays;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.StudentData;
import data.provides.LocaleUtilsTestData;
import data.services.LeadService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelfRegistrationStudent4Years extends TestBase {
  private String id = "selfReg4Year";

  LeadService leadService = new LeadService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.leads().set1_leadParent(id);
  }

  @Test(dataProvider = "validSelfRegistrationFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testSelfRegistrationStudent4Years(StudentData student) {
    String dataBirthday = DateISOToUsualDataString(DateWithCorrectionDays(-1465));
    app.student().logout();
    app.base().refresh();
    app.lkParentRecord().goHref(id);
    app.lkParentRecord().selfRegistrationForStudent4Years(student, dataBirthday);
    app.check()
        .textElement(By.xpath("//div[@class='info']"),
            "Я скоро свяжусь с вами, чтобы помочь с активацией кабинета,"
                + " записью на пробное занятие, и дам рекомендации для подготовки");
    app.base().goByHref(app.base().address() + "/login");
    app.base().login("superadmin", "111111");
    app.check().equalityOfTwoElements(data.leadService().findById(id).getNote(),
        "Данные ученика: \n\nИмя: Вася\nФамилия: Васин\nДень рождения: " +
            dataBirthday + "\nВозраст: 4\nПол: Женский\nЯзык "
            + "обучения: Русский\nУровень владения компьютером: Занимался программированием ранее\nСтрана:"
            + " Беларусь\nЧасовой пояс: GMT+03:00");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    leadService.DeleteById(leadService.findById(id));
  }
}
