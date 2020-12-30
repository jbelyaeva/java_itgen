package tests.lkParent;
/* T-52 */
/* К дефолтной семье добавлен ученик. В админке направлений у Scratch не стоит галочка АЗ.
 * Записать нового ученика на пробное на Scratch и убедиться, что кнопки установщика нет на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrialOnSkillWithoutAT extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(period);
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrialOnSkillWithoutAT() {
    app.lkParentRecord().recordOnTrail(1);
    app.check().notFindElement(app.lkParent().getBtnInstall());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
