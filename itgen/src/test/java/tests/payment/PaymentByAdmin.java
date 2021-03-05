package tests.payment;

import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentByAdmin extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test
  public void testPaymentByAdmin() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    app.payment().paymentAdmin("student");
    //  assertThat(app.student().findPictureSuccessPay(), equalTo(true)); разкоментить, когда будут
    // права
    // добавить проверку на пополнение баланса, когда будут права
    app.payment().goToFamily("newFamily");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().payment().family().student().parent();
  }
}
