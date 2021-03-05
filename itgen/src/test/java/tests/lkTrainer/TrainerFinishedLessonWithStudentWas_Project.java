package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.materials.MaterialChilds;
import data.model.schedule.Comments;
import data.model.schedule.FinishedChildLessons;
import data.model.schedule.FinishedLessons;
import data.model.schedule.Schedules;
import data.services.ScheduleService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerFinishedLessonWithStudentWas_Project extends TestBase {
  ScheduleService scheduleService = new ScheduleService();
  private String period = "";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyOnLesson().set1_StudentOnLessonWithMaterials("newSchedule");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerFinishedLessonWithStudentWas_Project() {
    Schedules before = app.dbschedules().schedules();
    FinishedChildLessons finishChildBefore = app.dbschedules().finishedChildLessons();
    FinishedLessons finishBefore = app.dbschedules().finishedLessons();
    Comments commentsBefore = app.dbschedules().comments();
    MaterialChilds materialChildsBefore = app.dbmaterial().materialChilds();

    app.trainer().finishedLessonWithWas_giveProject("newSchedule", "Жуки", "Лабиринт");
    app.trainer().gotoTask();

    Schedules after = app.dbschedules().schedules();
    FinishedChildLessons finishChildAfter = app.dbschedules().finishedChildLessons();
    FinishedLessons finishAfter = app.dbschedules().finishedLessons();
    Comments commentsAfter = app.dbschedules().comments();
    MaterialChilds materialChildsAfter = app.dbmaterial().materialChilds();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(finishChildAfter.size(), equalTo(finishChildBefore.size() + 1));
    assertThat(finishAfter.size(), equalTo(finishBefore.size() + 1));
    assertThat(commentsAfter.size(), equalTo(commentsBefore.size() + 1));
    assertThat(materialChildsAfter.size(), equalTo(materialChildsBefore.size() + 2));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().family().student().finishedLesson().material();
  }

  private void check(Schedules after) {
    TimeGeneral time = new TimeGeneral();
    long alreadyRun = 7200000;
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    scheduleService.DeleteById("ScheduleFinishLessonByTrainer");
    data.schedules().set38_FinishedSingleScheduleWithOneStudentOnTrail(period, "23");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
