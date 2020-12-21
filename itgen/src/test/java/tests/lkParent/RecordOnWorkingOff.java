package tests.lkParent;
/* T-102 */
/* К дефолтной семье добавлен ученик старше 7 лет. Вчера было завершено пробное, а затем так же
 * вчера им было пропущено занятие. Перейти в отработку нажав на кнопку Отработка, проверить:
 * Записаться на отработку -нет алертв, перекинуло в расписание, отображается лейбл Отработка
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnWorkingOff extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set11_YesterdayTwoLessonWasAndSkip_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnWorkingOff() {
    app.lkParent().reset();
    app.lkParent().recordOnWorkingOff();
    app.check()
        .equalityOfTwoElements(app.base().getURL(),
            app.base().address() + "/profile/newStudent?tab=schedule");
    app.check().notFindElement(app.lkParent().getBtnAssignWorking());
    app.check()
        .textElement(app.lkParent().getLabelWorkingOffOnThirdLessonInSchedule(), "отработка");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
