package tests.lkParent;
/* T-53 */
/* К дефолтной семье добавлен ученик. В админке направлений у Minecraft(21) стоит галочка АЗ.
 * Тренер ведет данное наравление.
 * Записать нового ученика на пробное, перейти на страницу установщика, нажав кнопку Установщик.
 * Проверить, что переход на нужную страницу
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageConfirmTransitionToPageInstall extends TestBase {

  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.trainerService().updateField("14", "skills", new String[]{"1", "2", "5", "21"});
    data.defFamily().set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(period);
    String id = data.skillsService().findBySkillId("21", "ru").getId();
    data.skills().set1_MinecraftWithAt(id);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPageConfirmTransitionToPageInstall() throws InterruptedException {
    app.check()
        .equalityOfTwoElements(app.lkParentRecord().goToPageInstall(21),
            "https://drive.google.com/drive/folders/1FntRoW1PkC_Qay4pGj_NNZ55KmDv00zy");
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
