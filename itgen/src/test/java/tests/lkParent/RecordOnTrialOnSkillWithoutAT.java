package tests.lkParent;
/* T-52 */
/* К дефолтной семье добавлен ученик. В админке направлений у Minecraft не стоит галочка АЗ.
 * Записать нового ученика на пробное и убедиться, что кнопки установщика нет на странице
 * подтверждения.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnTrialOnSkillWithoutAT extends TestBase {

  private final TaskService taskService = new TaskService();
  private final ScheduleService scheduleService = new ScheduleService();
  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();
  private final String period = "18:00 - 20:00";

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
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrialOnSkillWithoutAT() {
    app.lkParent().recordOnTrail(1);
    app.check().notFindElement(app.lkParent().getBtnInstall());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.drop();
    studentService.DeleteById("LKOnTrail");
    taskService.drop();
  }
}
