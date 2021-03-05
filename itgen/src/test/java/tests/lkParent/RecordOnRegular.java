package tests.lkParent;
/**
 * T-18 есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в 18.00 и
 * постоянное расписание на завтра в 18.00, на которое нужно записать ученика
 */
import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnRegular extends TestBase {

  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set9_LessonYesterdayFinished_RegularLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
            period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnRegular() {
    Schedules before = app.dbschedules().schedules();
    app.lkParentRecord().recordOnRegular();
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set4_RegularScheduleWithStudent(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
