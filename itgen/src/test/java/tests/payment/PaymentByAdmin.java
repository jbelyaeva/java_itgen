package tests.payment;

import app.testbase.TestBase;
import data.services.FamilyService;
import data.services.ParentService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentByAdmin extends TestBase {
  ParentService parentService = new ParentService();
  FamilyService familyService = new FamilyService();
  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("paymentAdmin", true, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .newStudent(
            "paymentAdminChild",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "paymentAdmin",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial");

    app.trParent()
        .newParent(
            "paymentAdminParent",
            "Родитель",
            "Новый",
            "AL",
            "Europe/Minsk",
            "ru",
            "paymentAdmin",
            "123456789",
            "mail1@list.ru",
            true);
  }

  @Test
  public void testPaymentByAdmin() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    app.payment().paymentAdmin("paymentAdminChild");
    //  assertThat(app.student().findPictureSuccessPay(), equalTo(true)); разкоментить, когда будут
    // права
    // добавить проверку на пополнение баланса, когда будут права
    app.payment().goToFamily("paymentAdmin");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    familyService.DeleteById("paymentAdmin");
    studentService.DeleteById("paymentAdminChild");
    parentService.DeleteById("paymentAdminParent");
  }
}
