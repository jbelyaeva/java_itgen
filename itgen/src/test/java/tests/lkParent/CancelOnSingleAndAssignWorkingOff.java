package tests.lkParent;
/*Т-24
 * отмена разового занятия тестовая ситуация: есть дефолтная семья, к которой добавлен ученик,
 * прошедший вчера успешно пробное в 18.00 и который записан на разовое расписание на сегодня,
 * которое начнется через 4 часаю. Есть занятие без учеников на завтра. Отменить разовое занятие с
 * отработкой позже. Проверить, что перекинуло в окно отработки.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOnSingleAndAssignWorkingOff extends TestBase {

  private final String period = "18:00 - 20:00";
  private final long fourHours = 14400000;

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set18_StudentAfterTrialWithLessonToday_LessonSingleTomorrowWithoutStudent_StudentAddInDefaultFamily(
            period, fourHours);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCancelOnSingleAndAssignWorkingOff() {
    app.lkParent().btnLogo();
    app.lkParentRecord().cancelSingleScheduleAndClickOnWorkingOffNow();
    app.check().textElement(By.xpath("//h2"), "Отработка занятия");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
