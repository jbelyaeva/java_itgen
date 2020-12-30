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

public class IncreaseBalanceByAdmin extends TestBase {
  ParentService parentService = new ParentService();
  PaymentService paymentService = new PaymentService();
  FamilyService familyService = new FamilyService();
  StudentService studentService = new StudentService();
  PaymentData paymentNew = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("increaseAdmin", true, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .newStudent(
            "increaseAdminChild",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "increaseAdmin",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");

    app.trParent()
        .newParent(
            "increaseAdminParent",
            "Родитель",
            "Новый",
            "AL",
            "Europe/Minsk",
            "ru",
            "increaseAdmin",
            "123456789",
            "mail1@list.ru",
            true);
  }

  @Test
  public void testIncreaseBalanceByAdmin() throws InterruptedException {
    app.goTo().menuStudents();
    Payments before = app.db().payments("increaseAdmin");
    app.payment().increaseAdmin("increaseAdminChild", "1");
    Payments after = app.db().payments("increaseAdmin");
    paymentNew = app.payment().getNewPaymentDB(before, after);
    checkDB(after, paymentNew.getId());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    familyService.DeleteById("increaseAdmin");
    studentService.DeleteById("increaseAdminChild");
    parentService.DeleteById("increaseAdminParent");
    paymentService.DeleteById(paymentNew.getId());
  }

  private void checkDB(Payments after, String id) {
    app.trPayment().newPayment(id, "increaseAdmin", "666", 1, 2, "Корректировка", true, 100);
    PaymentData paymentAdd = paymentService.findById(id);

    for (PaymentData paymentAfter : after) {
      if (paymentAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(paymentAfter).withAdded(paymentAdd)));
        return;
      }
    }
  }
}
