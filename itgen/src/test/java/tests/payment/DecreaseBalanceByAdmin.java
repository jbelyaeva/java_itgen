package tests.payment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import data.model.payment.PaymentData;
import data.model.payment.Payments;
import data.services.PaymentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DecreaseBalanceByAdmin extends TestBase {
  PaymentService paymentService = new PaymentService();
  PaymentData paymentNew = null;

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test
  public void testDecreaseBalanceByAdmin() {
    app.goTo().menuStudents();
    Payments before = app.db().payments("newFamily");
    app.payment().decreaseAdmin("student", "-1");
    Payments after = app.db().payments("newFamily");
    paymentNew = app.payment().getNewPaymentDB(before, after);
    checkDB(after, paymentNew.getId());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().payment().family().student().parent();
  }

  private void checkDB(Payments after, String id) {
    data.payments().set2_addPaymentInFamily("newFamily");
    //  app.trPayment().newPayment(id, "decreaseAdmin", "666", -1, 2, "Корректировка", true, 100);
    PaymentData paymentAdd = paymentService.findById(id);

    for (PaymentData paymentAfter : after) {
      if (paymentAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(paymentAfter).withAdded(paymentAdd)));
        return;
      }
    }
  }
}
