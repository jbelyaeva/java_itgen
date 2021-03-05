package tests.lkParent;
/* T-28 */
/* тестовая ситуация: есть дефолтная семья, к которой добавлен ученик с разовым занятием на завтра.
 * На главной странице у ребенка значек календаря и дата+время будущего занятия */

import static core.general.DateFormat.formatDDMMMM;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentsFutureLessonOnGeneralPage extends TestBase {

  private final String period = "21:00 - 23:00";
  private final String text = " 21:00 Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set4_SingleLessonTomorrowWithStudent_StudentAddInDefaultFamily(period);
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
    data.clean().taskAndSchedule().student();
  }
}
