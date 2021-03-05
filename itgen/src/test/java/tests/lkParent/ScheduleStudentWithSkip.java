package tests.lkParent;
/* T-94 */
/* К дефолтной семье добавлен ученик старше 7 лет. Вчера было завершено пробное, а затем так же
 * вчера им было пропущено занятие. Проверить, что
 * 1 в расписании на занятии стоит лейблы Пропуск, Завершено
 * 2 в расписании есть кнопка Назначить отработку
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleStudentWithSkip extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set11_YesterdayTwoLessonWasAndSkip_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleStudentWithSkip() {
    app.lkParent().reset();
    app.lkParent().clickByShowHistorySecondChild();
    app.lkParent().clickByTabSchedule();
    app.check().findElement(app.lkParent().getBtnAssignWorking());
    app.check().findElement(app.lkParent().scheduleLabelSkipped());
    app.check().findElement(app.lkParent().getScheduleLabelFinished());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
