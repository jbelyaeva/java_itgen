package tests.lkParent;
/*T-163
 * есть дефолтная семья, к которой добавлен ученик и разовое расписание ИФ на завтра в 18.00
 * (Бокша)  у ученика есть пропуск. Зайти в отработку и убедиться, что занятие Бокши не
 * отображается для записи
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnIFWorkingOff extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set16_YesterdayTwoLessonsWasAndAbort_StudentAddInDefaultFamily();
    data.schedules().set37_SingleIFScheduleWithoutStudent(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnIFWorkingOff() {
    app.lkParent().btnLogo();
    app.lkParentRecord().goInWorkingOff();
    app.check()
        .findElement(app.lkParentRecord().getLabelNotFoundLessonsForWorkingOff());
    app.lkParentRecord().closePopupWorkingOff();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
