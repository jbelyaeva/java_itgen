package tests.lkParent;
/* T-94 */
/* К дефолтной семье добавлен ученик старше 7 лет. Вчера было завершено пробное, а затем так же
 * вчера им было пропущено занятие. Проверить, что 1 в расписании на занятии стоит лейблы Пропуск, Завершено
 * 2 в расписании есть кнопка Назначить отработку
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleStudentWithSkip extends TestBase {

  private final StudentService studentService = new StudentService();
  String periodFirst = "10:00 - 12:00";
  String periodSecond = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedTrialSchedule", "14", "newStudent", "1");

    app.trScheduleYesterday()
        .finishedLesson(
            periodSecond,
            "FinishedSchedule",
            "14",
            "newStudent",
            "1",
            3,
            "skipped",
            true,
            false,
            false,
            "ru",
            120
        );

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleStudentWithSkip() {
    app.lkParent().reset();
    app.lkParent().clickByShowHistorySecondChild();
    app.lkParent().clickByTabSchedule();
    app.check().findElement(app.lkParent().getBtnAssighWorking());
    app.check().findElement(app.lkParent().getScheduleLabelSkipped());
    app.check().findElement(app.lkParent().getScheduleLabelFinished());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("newStudent");
    app.postClean().dropTaskAndSchedule();
  }
}
