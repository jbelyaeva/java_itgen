package tests.lkParent;
/* T-11 */
/*
 * В дефолтную семью добавлен ученик с расписанием на завтра. Зайти на главную страницу ЛК и ,нажав
 * на имя ученика, перейти в таб Настройки.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.ScheduleService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageTransitionFromStudentToSettings extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "21:00 - 23:00";
  String trainerId = "14";

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
            app.base().DateWithCorrectionDays(-1460),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "learning"
        );

    app.trScheduleTomorrow().SingleScheduleWithOneNewStudent(
        period,
        "newSchedule",
        trainerId,
        "newStudent",
        "1",
        "ru");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTransitionFromStudentToSettings() {
    app.lkParent().reset();
    app.lkParent().clickByNameStudent("newStudent");
    app.base().waitVisibilityOfElementLocated(5, app.lkParent().getLabelPersonalInformation());
    app.check().findElement(app.lkParent().getTabSettings());
    app.check()
        .textElement(app.lkParent().getLabelPersonalInformation(), "Персональная информация");
    app.check().textElement(app.lkParent().getLabelLoginAndPassword(), "Логин и пароль ученика");
    app.check().textElement(app.lkParent().getLabelHowToJoinInstruction(),
        "Как зайти в личный кабинет ученика:");
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("newStudent");
    scheduleService.drop();
  }
}
