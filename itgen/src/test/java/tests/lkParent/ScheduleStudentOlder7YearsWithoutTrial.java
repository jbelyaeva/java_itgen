package tests.lkParent;
/* T-99 */
/* К дефолтной семье добавлен ученик старше 7 лет.
 * Проверить, что в расписании кнопка Записаться на пробное
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleStudentOlder7YearsWithoutTrial extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set13_addNewStudentOlder7Years();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleStudentOlder7YearsWithoutTrial() {
    app.lkParent().reset();
    app.lkParent().clickByShowHistoryFirstChild();
    app.lkParent().clickByTabSchedule();
    app.check().findElement(app.lkParent().getBtnRecordOnTrailInSchedule());
    app.check().notFindElement(app.lkParent().getBtnRecordOnLesson());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student();
  }
}
