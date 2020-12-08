package tests.lkParent;
/* T-28 */
/* тестовая ситуация: есть дефолтная семья, к которой добавлен ученик с разовым занятием на завтра.
 * На главной странице у ребенка значек календаря и дата+время будущего занятия */

import static core.general.DateFormat.formatDDMMMM;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.ScheduleService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentsFutureLessonOnGeneralPage extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "21:00 - 23:00";
  String text = " 21:00 Scratch";

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
        "14",
        "newStudent",
        "1",
        "ru");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentsFutureLessonOnGeneralPage() {
    app.lkParent().reset();
    app.check().findElement(app.lkParent().getIconCalendar());
    String lesson = formatDDMMMM(app.base().DateWithCorrectionDays(+1)) + text;
    app.check().textElement(app.lkParent().getLabelFutureLessonOnGeneralPage(), lesson);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("newStudent");
    scheduleService.drop();
  }
}
