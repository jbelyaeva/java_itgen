package tests.payment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import data.model.payment.PaymentData;
import data.model.payment.Payments;
import data.services.FamilyService;
import data.services.ParentService;
import data.services.PaymentService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DecreaseBalanceByAdmin extends TestBase {
  ParentService parentService = new ParentService();
  PaymentService paymentService = new PaymentService();
  FamilyService familyService = new FamilyService();
  StudentService studentService = new StudentService();
  PaymentData paymentNew = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("decreaseAdmin", true, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .NewStudent(
            "decreaseAdminChild",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "decreaseAdmin",
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
            "decreaseAdminParent",
            "Родитель",
            "Новый",
            "AL",
            "Europe/Minsk",
            "ru",
            "decreaseAdmin",
            "123456789",
            "mail1@list.ru",
            true);
  }

  @Test
  public void testDecreaseBalanceByAdmin() throws InterruptedException {
    app.goTo().menuStudents();
    Payments before = app.db().payments("decreaseAdmin");
    app.payment().decreaseAdmin("decreaseAdminChild", "-1");
    Payments after = app.db().payments("decreaseAdmin");
    paymentNew = app.payment().getNewPaymentDB(before, after);
    checkDB(after, paymentNew.getId());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    familyService.DeleteById("decreaseAdmin");
    studentService.DeleteById("decreaseAdminChild");
    parentService.DeleteById("decreaseAdminParent");
    paymentService.DeleteById(paymentNew.getId());
  }

  private void checkDB(Payments after, String id) {
    app.trPayment().newPayment(id, "decreaseAdmin", "666", -1, 2, "Корректировка", true, 100);
    PaymentData paymentAdd = paymentService.findById(id);

    for (PaymentData paymentAfter : after) {
      if (paymentAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(paymentAfter).withAdded(paymentAdd)));
        return;
      }
    }
  }
}
