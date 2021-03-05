package tests.schedule;
/* T-162 */
/* новая семья. Вчера   было завершено пробное, а затем так же
 * вчера учеником было пропущено занятие.
 * Есть на завтра два занятия ИФ и ИФГ
 * Перейти в расписание ученика, нажать на кнопку Отработка и записаться на отработку
 * Проверить:
 * предлагается для записи на отработку только занятие ИФ
 * записывает без ошибок, перекидывает в календарь и есть занятие помечено как Отработка
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordInIFOnWorkingOff extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String periodFirst = "10:00 - 12:00";
    String periodSecond = "18:00 - 20:00";
    data.newFamilyWithSingleLessons().set7_FamilyAndYesterdayWasTrialWasSkipSingleIFLesson();
    data.schedules().set3_SingleScheduleWithoutStudent(periodFirst, "23");
    data.schedules().set37_SingleIFScheduleWithoutStudent(periodSecond, "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordIFOnWorkingOff() {
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("newStudent");
    app.schedule().btnSchedule();
    app.schedule().btnWorkingOff();

    //проверка, что предлагает только группу ИФ
    app.check().countElements(app.schedule().getLessonsForWorkingOff(), 1);
    app.check().textContent(app.schedule().getTrainerInWorkingOffPopup(), "Бокша Настя");

    app.schedule().recordOnWorkingOff();
    app.schedule().checkShowWorkingOff();
    //перекинуло в календарь ученика и в календаре есть запись на занятие, помеченное Отработка
    app.check().findElement(app.schedule().getLabelWorkingOffInCalendar());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
