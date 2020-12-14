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
import data.services.StudentService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleStudentWithButtonInstall extends TestBase {

  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();
  private final String periodFirstLesson = "14:00 - 16:00";
  private final String periodSecondLesson = "20:00 - 22:00";
  private final String descrition = "Ученик будет конструировать 3D миры, "
      + "строить логические цепочки, получит базовые знание по решению задач на программирование "
      + "и разработке проектов";

  @BeforeMethod
  public void ensurePreconditions() {

    app.trStudent()
        .newStudent(
            "newStudent",
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
            "noTrial");

    app.trScheduleTomorrow().SingleScheduleWithOneStudentRecordOnTrail(
        periodFirstLesson,
        "first",
        "14",
        "newStudent",
        "21",
        "ru");

    app.trScheduleTomorrow().SingleScheduleWithOneStudentRecordOnTrail(
        periodSecondLesson,
        "second",
        "14",
        "newStudent",
        "1",
        "ru");

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
  public void testScheduleStudentWithButtonInstall() {
    app.lkParent().reset();
    app.lkParent().btnSchedule();
    app.base().moveToElement(app.lkParent().getCellLightCalendar());
    app.check().findElement(app.lkParent().getBtnInstallInScheduleTomorrow());
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("newStudent");
    app.postClean().dropTaskAndSchedule();
  }
}
