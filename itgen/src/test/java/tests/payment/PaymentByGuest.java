package tests.payment;

import static app.appmanager.ApplicationManager.properties;
import static org.testng.AssertJUnit.assertEquals;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*T-258
 * оплатить пакетом под гостем:
 * 1 сначала зайти под родителем,
 * 2 перейти в пакеты,
 * 3 скопировать ссылку
 * 4 разлогиниться
 * 5 открыть ссылку
 * 6 оплатить
 */
public class PaymentByGuest extends TestBase {

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    /*app.trScheduleYesterday()
        .finishingFirstTrialLesson(period, "FinishedSchedule", "14", "paymentByGuest", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "paymentByGuest",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            1,
            "trialFinished",
            new String[]{"1"},
            new String[]{"1"},
            new int[]{1, 120});*/
    String period = "18:00 - 20:00";
    data.finishedLessonWithProject()
        .set1_LessonYesterdayFinishedWithProject_StudentAddInDefaultFamily(
            period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPaymentByGuest() {
    app.lkParent().reset();
    app.payment().goToShopByParent();
    app.payment().goToShopByGuest();

    assertEquals("Родитель Де*******", app.payment().getPayer());

    app.payment().paymentByGuest();
    app.goTo().goByHref(properties.getProperty("web.baseUrl"));
    app.session()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));

    //  assertThat(app.student().findPictureSuccessPay(), equalTo(true)); разкоментить, когда будут
    // права
    // добавить проверку на пополнение баланса, когда будут права, а затем вернуть баланс в 0
    //  app.payment().goToBack("paymentAdmin");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().payment().material().finishedLesson().student();
  }
}
