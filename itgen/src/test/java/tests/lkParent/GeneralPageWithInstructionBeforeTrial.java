package tests.lkParent;
/* T-9 */
/* тестовая ситуация: есть дефолтная семья, к которой добавлен ученик записанный на пробное
  Нажать Подготовка к пробному - открылась страница с инструкцией */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageWithInstructionBeforeTrial extends TestBase {

  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow()
        .SingleScheduleWithOneStudentRecordOnTrail(
            period, "newStudent", "14", "newStudent", "1", "ru");

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
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "withTrial"
        );
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageWithInstructionBeforeTrial() {
    app.lkParent().reset();
    app.lkParent().btnPrepare();
    app.check().textElement(app.lkParent().getLabelWaiteOnLessons(), "Ждем на занятии");
    app.check().textElement(app.lkParent().getLabelRecomendationForFirstLesson(),
        "Рекомендации для первого занятия");
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.postClean().dropTaskAndSchedule();
    studentService.DeleteById("newStudent");
  }
}
