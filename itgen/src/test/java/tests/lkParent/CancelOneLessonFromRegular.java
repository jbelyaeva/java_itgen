package tests.lkParent;
/**
 * тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
 * 18.00 и который записан на постоянное расписание на завтра в 18.00. Отменить ученику первое
 * занятия из постоянного расписания.
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOneLessonFromRegular extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set1_LessonYesterdayFinished_RegularLessonTomorrowWithStudent_StudentAddInDefaultFamily(
            period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCancelOneLessonFromRegular() {
    app.lkParent().btnLogo();
    Schedules before = app.dbschedules().schedules();
    app.lkParent().cancelOneLessonInRegularSchedule();
    Schedules after = app.dbschedules().schedules();
    //контроль записи в бд
    assertThat(after.size(), equalTo(before.size()));
    data.schedules().set1_RegularScheduleWithoutStudentOnFirstLesson(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
