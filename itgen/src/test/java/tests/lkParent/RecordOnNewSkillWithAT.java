package tests.lkParent;
/* T-54 */
/* К дефолтной семье добавлен ученик. У него вчера было пробное по Scratch. В админке направлений у
 * Minecraft стоит галочка АЗ. Тренер ведет данное наравление.
 * Записать нового ученика на новое направление Minecraft и убедиться, что кнопки установщика есть на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnNewSkillWithAT extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set7_LessonYesterdayFinished_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
            period);
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
    data.skills().set1_MinecraftWithAt();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnNewSkillWithAT() {
    app.lkParent().reset();
    app.lkParent().recordOnSingleOnNewSkill("Minecraft");
    app.check().findElement(app.lkParent().getBtnInstall());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
