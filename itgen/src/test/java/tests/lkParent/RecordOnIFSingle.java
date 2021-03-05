package tests.lkParent;
/*T-160
 * Есть дфолтная семья, к которой добавлен ученик, прошедший вчера пробное в 18.00 и разовое
 * расписание ИФ на завтра в 18.00
 * Проверить при записи на разовое не предлагается расписание с ИФ
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnIFSingle extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set12_LessonYesterdayFinished_StudentAddInDefaultFamily(period);
    data.schedules().set37_SingleIFScheduleWithoutStudent(period, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnSingle() {
    app.lkParent().btnLogo();
    app.lkParentRecord().recordOnIFSingle();
    app.check().findElement(app.lkParentRecord().getLabelNotFoundLessonsForSingle());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
