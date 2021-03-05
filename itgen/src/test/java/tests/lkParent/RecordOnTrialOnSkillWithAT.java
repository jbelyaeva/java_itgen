package tests.lkParent;
/* T-51 */
/* К дефолтной семье добавлен ученик. В админке направлений у Minecraft стоит галочка АЗ.
 * Тренер ведет данное наравление.
 * Записать нового ученика на пробное и убедиться, что кнопки установщика есть на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrialOnSkillWithAT extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(period);
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
    data.skills().set1_MinecraftWithAt(data.skillsService().findBySkillId("21", "ru").getId());
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrialOnSkillWithoutAT() {
    app.lkParentRecord().recordOnTrail(21);
    app.check().findElement(app.lkParent().getBtnInstall());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
