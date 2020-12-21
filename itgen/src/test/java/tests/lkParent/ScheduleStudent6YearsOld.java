package tests.lkParent;
/* T-98 */
/* К дефолтной семье добавлен ученик младше 7 лет.
 * Проверить, что в расписании нет кнопок Записаться на занятия и записаться на пробное
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleStudent6YearsOld extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set3_addNewStudentYanger7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleStudent6YearsOld() {
    app.lkParent().reset();
    app.lkParent().btnShowSchedule();
    app.check().notFindElement(app.lkParent().getBtnRecordOnTrailInSchedule());
    app.check().notFindElement(app.lkParent().getBtnRecordOnLesson());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.studentService().DeleteById("newStudent");
  }
}
