package tests.lkParent;
/* T-55 */
/* К дефолтной семье добавлен ученик. У него вчера было пробное по Minecraft. В админке направлений у
 * Minecraft стоит галочка АЗ. Тренер ведет данное наравление.
 * Записать добавленного ученика опять на направление Minecraft и убедиться, что кнопки установщика нет на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnOldSkillWithAT extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.defFamily()
        .set8_LessonMinecaftYesterdayFinished_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
            period);
    //тренер ведет данное направление
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
    data.skills().set1_MinecraftWithAt(data.skillsService().findBySkillId("21", "ru").getId());
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnOldSkillWithAT() {
    app.lkParent().reset();
    app.lkParentRecord().recordOnSingle();
    app.check().notFindElement(app.lkParent().getCellLightCalendar());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
