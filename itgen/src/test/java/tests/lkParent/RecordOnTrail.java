package tests.lkParent;
/**
 * T-17 есть дефолтная семья, к которой добавлен ученик и разовое расписание на завтра в 18.00, на
 * которое нужно записать добавленного ученика
 */
import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrail extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrail() {
    Schedules before = app.dbschedules().schedules();
    app.lkParentRecord().recordOnTrail(1);
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set6_SingleScheduleWithOneStudentRecordOnTrailOnScratch(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
