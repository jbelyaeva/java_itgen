package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.schedule.FinishedChildLessons;
import data.model.schedule.FinishedLessons;
import data.model.schedule.Schedules;
import data.services.FamilyService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.PaymentService;
import data.services.ScheduleService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerFinishedLessonWithStudentNotWas extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  PaymentService paymentService = new PaymentService();
  private final TimeGeneral time = new TimeGeneral();
  FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  FinishedLessonService finishedLessonService = new FinishedLessonService();
  private String period = "";
  private final long alreadyRun = 7200000; // 2 часа идет занятие

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "finishLessonByTrainer",
            "23",
            "finishLessonByTrainer",
            "1",
            "ru");

    app.trFamily().newFamily("finishLessonByTrainer", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .NewStudent(
            "finishLessonByTrainer",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "finishLessonByTrainer",
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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testFinishedLessonWithStudentNotWas() {
    Schedules before = app.dbschedules().schedules();
    FinishedChildLessons finishChildBefore = app.dbschedules().finishedChildLessons();
    FinishedLessons finishBefore = app.dbschedules().finishedLessons();

    app.trainer().finishedLessonWithNotWas("finishLessonByTrainer");
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
    scheduleService.DeleteById("finishLessonByTrainer");
    studentService.DeleteById("finishLessonByTrainer");
    familyService.DeleteById("finishLessonByTrainer");
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    paymentService.drop();
  }

  private void check(Schedules after) {
    scheduleService.DeleteById("finishLessonByTrainer");
    app.trScheduleToday()
        .FinishedSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "finishLessonByTrainer",
            "14",
            "finishLessonByTrainer",
            "1",
            "ru",
            "skipped");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
