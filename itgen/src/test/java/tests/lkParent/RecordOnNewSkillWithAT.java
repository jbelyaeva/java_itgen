package tests.lkParent;
/* T-54 */
/* К дефолтной семье добавлен ученик. У него вчера было пробное по Scratch. В админке направлений у
 * Minecraft стоит галочка АЗ. Тренер ведет данное наравление.
 * Записать нового ученика на новое направление Minecraft и убедиться, что кнопки установщика есть на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnNewSkillWithAT extends TestBase {

  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();
  private final String period = "18:00 - 20:00";
  private final String descrition = "Ученик будет конструировать 3D миры, "
      + "строить логические цепочки, получит базовые знание по решению задач на программирование "
      + "и разработке проектов";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "RecordOnNewSkill", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "RecordOnNewSkill",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);

    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "RecordOnNewSkill", "14");

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
  public void testRecordOnNewSkillWithAT() {
    app.lkParent().reset();
    app.lkParent().recordOnSingleOnNewSkill("Minecraft");
    app.check().findElement(app.lkParent().getBtnInstall());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("RecordOnNewSkill");
    app.postClean().dropTaskAndSchedule();
  }
}
