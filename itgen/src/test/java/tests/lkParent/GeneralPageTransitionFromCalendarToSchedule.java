package tests.lkParent;
/* T-10 */
/* В дефолтную семью добавлен ученик с расписанием на завтра к Бокше. Зайти на главную страницу ЛК и
 * ,нажав на календарь, перейти в расписание. Проверить, что в расписании есть урок на завтра с
 * правильным временем, тренером и лейблами
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageTransitionFromCalendarToSchedule extends TestBase {
  private final String period = "21:00 - 23:00";
  TrainerData trainer = null;

  @BeforeMethod
  public void ensurePreconditions() {
    trainer = data.trainerService().findById("14");
    data.defFamily().set4_SingleLessonTomorrowWithStudent_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTransitionFromCalendarToSchedule() {
    app.lkParent().reset();
    app.lkParent().btnSchedule();
    app.check().findElement(app.lkParent().getTabSchedule());
    app.lkParent().foundSchedule();
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
    data.clean().taskAndSchedule().student();
  }
}
