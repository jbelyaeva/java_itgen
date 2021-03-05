package tests.lkParent;
/* T-23*
 * тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное
 * в 18.00 и который записан на постоянное расписание на завтра в 18.00. Отменить ученику все
 * занятия из постоянного расписания
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOnRegular extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set1_LessonYesterdayFinished_RegularLessonTomorrowWithStudent_StudentAddInDefaultFamily(
            period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCancelOnRegular() {
    app.lkParent().btnLogo();
    Schedules before = app.dbschedules().schedules();
    app.lkParentRecord().cancelLessonsInRegularSchedule();
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set2_RegularScheduleWithoutStudents(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
