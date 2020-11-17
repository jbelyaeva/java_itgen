package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.TimeGeneral;
import data.model.schedule.Schedules;
import data.services.FamilyService;
import data.services.ScheduleService;
import data.services.StudentService;
import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerLessonEnvelopes extends TestBase {
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow());
    app.trScheduleToday()
        .SingleScheduleWithOneStudentOnTrail(period, "envelope", "23", "envelop", "1", "ru");

    app.trFamily().newFamily("envelop", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .NewStudent(
            "envelop",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "envelop",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2);
  }

  @Test
  public void testTrainerLessonEnvelopes() {
    app.trainer().gotoSchedule();
    Schedules before = app.dbschedules().schedules();
    app.trainer().envelopes("envelop");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    app.trainer().refresh();
    app.trainer().gotoTask();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.drop();
    studentService.DeleteById("envelop");
    familyService.DeleteById("envelop");
  }
}
