package tests.lkParent;
/* T-98 */
/* К дефолтной семье добавлен ученик 6 лет.
 * Проверить, что в расписании нет кнопок Записаться на занятия и записаться на пробное
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleStudent6YearsOld extends TestBase {

  private final StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {

    app.trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-2160),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial");
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
    studentService.DeleteById("newStudent");
  }
}
