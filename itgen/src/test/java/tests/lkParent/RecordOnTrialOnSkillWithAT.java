package tests.lkParent;
/* T-51 */
/* К дефолтной семье добавлен ученик. В админке направлений у Minecraft стоит галочка АЗ.
 * Тренер ведет данное наравление.
 * Записать нового ученика на пробное и убедиться, что кнопки установщика есть на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrialOnSkillWithAT extends TestBase {

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
            "http://google.com",
            true,
            1,
            2);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrialOnSkillWithoutAT() {
    app.lkParent().recordOnTrail(21);
    app.check().findElement(app.lkParent().getBtnInstall());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("LKOnTrail");
    app.postClean().dropTaskAndSchedule();
  }
}
