package tests.payment;

import static app.appmanager.ApplicationManager.properties;
import static org.testng.AssertJUnit.assertEquals;

import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentByGuest extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(period, "FinishedSchedule", "14", "paymentByGuest", "1");

    app.trStudent()
        .StudentAddDefaultFamilyAfterLesson(
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
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPaymentByGuest() {
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
    scheduleService.DeleteById("FinishedSchedule");
    studentService.DeleteById("paymentByGuest");

    Tasks tasks = app.dbschedules().tasksComposition("paymentByGuest");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
