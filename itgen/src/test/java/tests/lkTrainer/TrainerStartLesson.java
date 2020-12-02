package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotNull;

import app.testbase.TestBase;
import core.general.TimeGeneral;
import data.model.schedule.Schedules;
import data.services.FamilyService;
import data.services.ScheduleService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerStartLesson extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow());
    app.trScheduleToday()
        .SingleScheduleWithOneStudentOnTrail(
            period, "startLessonByTrainer", "23", "startLessonByTrainer", "1", "ru");

    app.trFamily().newFamily("startLessonByTrainer", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .NewStudent(
            "startLessonByTrainer",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "startLessonByTrainer",
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
  }

  @Test
  public void testTrainerStartLesson() {
    app.trainer().gotoSchedule();
    Schedules before = app.dbschedules().schedules();
    app.trainer().startLesson("startLessonByTrainer");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.drop();
    studentService.DeleteById("startLessonByTrainer");
    familyService.DeleteById("startLessonByTrainer");
  }

  private void check(Schedules after) {
    // проверка, что появилось поле startedAt
    assertNotNull(scheduleService.findById("startLessonByTrainer").getSlots().get(0).getStartedAt());

    scheduleService.DeleteById("startLessonByTrainer");
    app.trScheduleToday()
        .SingleScheduleWithOneStudentOnTrail(
            period, "startLessonByTrainer", "23", "startLessonByTrainer", "1", "ru");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
