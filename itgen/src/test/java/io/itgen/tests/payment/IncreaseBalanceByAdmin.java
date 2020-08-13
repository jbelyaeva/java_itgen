package io.itgen.tests.payment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.PaymentData;
import io.itgen.model.Payments;
import io.itgen.services.FamilyService;
import io.itgen.services.ParentService;
import io.itgen.services.PaymentService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IncreaseBalanceByAdmin extends TestBase {
  ParentService parentService = new ParentService();
  PaymentService paymentService = new PaymentService();
  FamilyService familyService = new FamilyService();
  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("increaseAdmin", true, "txa");

    app.trStudent()
        .newStudent(
            "increaseAdminChild",
            "Володя",
            "Володин",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "increaseAdmin");

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
  public void testPaymentByAdmin() {
    app.goTo().menuStudents();
    Payments before = app.db().payments("increaseAdmin");
    app.payment().increaseAdmin("increaseAdminChild", "1");
    Payments after = app.db().payments("increaseAdmin");
    PaymentData paymentNew = app.payment().getNewPaymentDB(before, after);
    checkDB(after, paymentNew.getId());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    familyService.findByIdAndDelete("increaseAdmin");
    studentService.findByIdAndDelete("increaseAdminChild");
    parentService.findByIdAndDelete("increaseAdminParent");
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
