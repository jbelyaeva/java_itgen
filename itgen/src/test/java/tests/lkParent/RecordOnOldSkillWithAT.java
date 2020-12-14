package tests.lkParent;
/* T-55 */
/* К дефолтной семье добавлен ученик. У него вчера было пробное по Minecraft. В админке направлений у
 * Minecraft стоит галочка АЗ. Тренер ведет данное наравление.
 * Записать добавленного ученика опять на направление Minecraft и убедиться, что кнопки установщика нет на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnOldSkillWithAT extends TestBase {

  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();
  private final String descrition = "Ученик будет конструировать 3D миры, "
      + "строить логические цепочки, получит базовые знание по решению задач на программирование "
      + "и разработке проектов";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "RecordOnOldSkill",
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
            "21",
            2,
            1,
            "trialFinished",
            "21",
            "21",
            1);

    String period = "18:00 - 20:00";
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(period, "ScheduleYesterday", "14", "RecordOnOldSkill", "21");

    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "RecordOnOldSkill", "14");

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
  public void testRecordOnOldSkillWithAT() {
    app.lkParent().reset();
    app.lkParent().recordOnSingle();
    app.check().notFindElement(app.lkParent().getCellLightCalendar());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("RecordOnOldSkill");
    app.postClean().dropTaskAndSchedule();
  }
}
