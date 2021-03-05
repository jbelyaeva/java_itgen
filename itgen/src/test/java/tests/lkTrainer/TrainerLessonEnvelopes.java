package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.TimeGeneral;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerLessonEnvelopes extends TestBase {
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow());
    data.schedules().set8_1_TodaySingleScheduleWithStudentOnTrial(period, "newSchedule", "student");
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test
  public void testTrainerLessonEnvelopes() {
    app.trainer().gotoSchedule();
    Schedules before = app.dbschedules().schedules();
    app.trainer().envelopes("newSchedule");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    app.trainer().refresh();
    app.trainer().gotoTask();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().family().student();
  }
}
