package tests.lkParent;
/* T-53 */
/* К дефолтной семье добавлен ученик. В админке направлений у Minecraft стоит галочка АЗ.
 * Тренер ведет данное наравление.
 * Записать нового ученика на пробное, перейти на страницу установщика, нажав кнопку Установщик.
 * Проверить, что переход на нужную страницу
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageConfirmTransitionToPageInstall extends TestBase {

  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();
  private final String period = "18:00 - 20:00";
  private final String descrition = "Ученик будет конструировать 3D миры, "
      + "строить логические цепочки, получит базовые знание по решению задач на программирование "
      + "и разработке проектов";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "LKOnTrail", "14");

    app.trStudent()
        .newStudent(
            "LKOnTrail",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial"
        );

    trainerService.updateField("14", "skills", new String[]{"1", "2", "5", "21"});

    int[] age = {5, 7};
    String[] state = {"hidden", "visible"};
    app.trSkill()
        .updateSkill(
            "SHnv3uDTCS9orGGkM",
            "ru", "Minecraft",
            "visible",
            descrition,
            7,
            age,
            state,
            "21",
            "https://drive.google.com/drive/folders/1FntRoW1PkC_Qay4pGj_NNZ55KmDv00zy",
            true,
            1,
            2);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPageConfirmTransitionToPageInstall() throws InterruptedException {
    app.check()
        .equalityOfTwoElements(app.lkParent().goToPageInstall(21),
            "https://drive.google.com/drive/folders/1FntRoW1PkC_Qay4pGj_NNZ55KmDv00zy");
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("LKOnTrail");
    app.postClean().dropTaskAndSchedule();
  }
}
