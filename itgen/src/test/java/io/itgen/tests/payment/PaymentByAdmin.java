package io.itgen.tests.payment;

import io.itgen.services.FamilyService;
import io.itgen.services.ParentService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentByAdmin extends TestBase {
  ParentService parentService = new ParentService();
  FamilyService familyService = new FamilyService();
  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("paymentAdmin", true, "txa");

    app.trStudent()
        .newStudent(
            "paymentAdminChild",
            "Володя",
            "Володин",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "paymentAdmin");

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
    studentService.findByIdAndDelete("paymentAdminChild");
    parentService.DeleteById("paymentAdminParent");
  }
}
