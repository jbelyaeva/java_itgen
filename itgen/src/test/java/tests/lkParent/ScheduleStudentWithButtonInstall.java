package tests.lkParent;
/* T-56 */
/* К дефолтной семье добавлен ученик. В админке направлений у
 * Minecraft стоит галочка АЗ, у Скрейч - не стоит. Тренер Настя ведет оба наравления.
 * Новый ученик записан на завтра в 14:00-16:00 на майнкрафт к Насте
 * Новый ученик записан на завтра в 20:00-22:00 на скрейч к Насте
 * Под родителем убедиться, что кнопка установщика отображается в расписании ученика на пробном.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleStudentWithButtonInstall extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set14_TomorrowTwoTrialLessons_StudentAddInDefaultFamily();
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
    data.skills().set1_MinecraftWithAt(data.skillsService().findBySkillId("21", "ru").getId());
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleStudentWithButtonInstall() {
    app.lkParent().reset();
    app.lkParent().btnSchedule();
    app.lkParent().foundSchedule();
    app.base().moveToElement(app.lkParent().getCellLightCalendar());
    app.check().findElement(app.lkParent().getBtnInstallInScheduleTomorrow());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
