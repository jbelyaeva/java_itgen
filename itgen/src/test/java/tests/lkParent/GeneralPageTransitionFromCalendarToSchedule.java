package tests.lkParent;
/* T-10 */
/**
 * В дефолтную семью добавлен ученик с расписанием на завтра. Зайти на главную страницу ЛК и ,нажав
 * на календарь, перейти в расписание. Проверить, что в расписании есть урок на завтра с правильным
 * временем, тренером и лейблами
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageTransitionFromCalendarToSchedule extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  TrainerService trainerService = new TrainerService();
  StudentService studentService = new StudentService();
  String period = "21:00 - 23:00";
  String trainerId = "14";
  TrainerData trainer = null;

  @BeforeMethod
  public void ensurePreconditions() {
    trainer = trainerService.findById(trainerId);
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
            app.base().DateWithCorrectionDays(-1460),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "learning"
        );

    app.trScheduleTomorrow().SingleScheduleWithOneNewStudent(
        period,
        "newSchedule",
        trainerId,
        "newStudent",
        "1",
        "ru");
  }

  @Test(retryAnalyzer = RunTestAgain.class, enabled = false)
  public void testGeneralPageTransitionFromCalendarToSchedule() {
    app.lkParent().reset();
    app.lkParent().btnSchedule();
    app.check().findElement(app.lkParent().getTabSchedule());
    app.check().textElement(app.lkParent().getSchedulePeriodLesson(), period);
    app.check().textElement(app.lkParent().getScheduleTrainerOnLesson(),
        trainer.getLastName() + " " + trainer.getFirstName());
    app.check().textElement(app.lkParent().getScheduleSkillOnLesson(), "Scratch");
    app.check().findElement(app.lkParent().getScheduleLabelNewOnLesson());
    app.check().findElement(app.lkParent().getScheduleLabelPlannedOnLesson());
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("newStudent");
    scheduleService.drop();
  }
}
