package tests.lkParent;
/*T-20
 * есть дефолтная семья, к которой добавлен ученик, прошедший вчера успешно
 * пробное в 18.00 и который записан на разовое расписание на завтра в 18.00
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOnSingle extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set2_LessonYesterdayFinished_SingleLessonTomorrowWithStudent_StudentAddInDefaultFamily(
            period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCancelOnSingle() {
    app.lkParent().btnLogo();
    Schedules before = app.dbschedules().schedules();
    app.lkParentRecord().cancelLessonInSingleSchedule();
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set3_SingleScheduleWithoutStudent(period, "14");
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
