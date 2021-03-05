package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotNull;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.schedule.Schedules;
import data.services.ScheduleService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerStartLesson extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow());
    data.newFamily().set7_FamilyWithStudent();
    data.schedules()
        .set8_1_TodaySingleScheduleWithStudentOnTrial(period, "newSchedule", "newStudent");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerStartLesson() {
    app.trainer().gotoSchedule();
    Schedules before = app.dbschedules().schedules();
    app.trainer().startLesson("newSchedule");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family();
  }

  private void check(Schedules after) {
    // проверка, что появилось поле startedAt
    assertNotNull(scheduleService.findById("newSchedule").getSlots().get(0).getStartedAt());

    scheduleService.DeleteById("newSchedule");
    data.schedules().set8_TodaySingleScheduleWithStudentOnTrial(period, "newSchedule", "23");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
