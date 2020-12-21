package tests.lkParent;
/* T-100 */
/* К дефолтной семье добавлен ученик старше 7 лет. Вчера было завершено пробное, а затем так же
 * вчера им было пропущено занятие. Перейти в отработку нажав на кнопку Отработка, проверить:
 * Группа не выбрана - кнопка записаться задизэйблена
 * Выбрать занятие - кнопка активна
 * Нажать назад - перекинуло в расписание
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkingOffWithLessons extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set11_YesterdayTwoLessonWasAndSkip_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWorkingOffWithLessons() {
    app.lkParent().reset();
    app.lkParent().goInWorkingOff();
    app.check().onDisabled(app.lkParent().getBtnAssignInWindowWorkingOff());
    app.lkParent().selectLessonForWorkingOff();
    app.check().onNotDisabled(app.lkParent().getBtnAssignInWindowWorkingOff());
    app.lkParent().clickByBack();
    app.check()
        .equalityOfTwoElements(app.base().getURL(),
            app.base().address() + "/profile/newStudent?tab=schedule");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.studentService().DeleteById("newStudent");
    data.postClean().taskAndSchedule();
  }
}
