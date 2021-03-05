package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.schedule.FinishedChildLessons;
import data.model.schedule.FinishedLessons;
import data.model.schedule.Schedules;
import data.services.ScheduleService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerFinishedLessonWithStudentNotWas extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";
  private final long alreadyRun = 7200000; // 2 часа идет занятие

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testFinishedLessonWithStudentNotWas() {
    Schedules before = app.dbschedules().schedules();
    FinishedChildLessons finishChildBefore = app.dbschedules().finishedChildLessons();
    FinishedLessons finishBefore = app.dbschedules().finishedLessons();
    app.trainer().gotoSchedule();
    app.trainer().finishedLessonWithNotWas("newSchedule");
    app.trainer().gotoTask();
    Schedules after = app.dbschedules().schedules();
    FinishedChildLessons finishChildAfter = app.dbschedules().finishedChildLessons();
    FinishedLessons finishAfter = app.dbschedules().finishedLessons();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(finishChildAfter.size(), equalTo(finishChildBefore.size() + 1));
    assertThat(finishAfter.size(), equalTo(finishBefore.size() + 1));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() throws InterruptedException {
    Thread.sleep(10000);
    data.clean().taskAndSchedule().student().family().finishedLesson().payment();
  }

  private void check(Schedules after) {
    scheduleService.DeleteById("newSchedule");
    data.schedules().set40_SkippedSingleScheduleWithOneStudentOnTrail(period, "23");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
