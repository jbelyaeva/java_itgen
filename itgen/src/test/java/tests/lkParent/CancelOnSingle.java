package tests.lkParent;
// отмена разового занятия
// тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера успешно
// пробное в 18.00 и который записан на разовое расписание на завтра в 18.00

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.services.ScheduleService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOnSingle extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkCancelLessonInSingleSchedule", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "LkCancelLessonInSingleSchedule",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "learning",
            "1",
            "1",
            1);

    app.trScheduleTomorrow()
        .SingleScheduleWithOneStudent(
            period,
            "LkCancelLessonInSingleSchedule",
            "14",
            "LkCancelLessonInSingleSchedule",
            "1",
            "ru");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCancelOnSingle() {
    app.lkParent().btnLogo();
    Schedules before = app.dbschedules().schedules();
    app.lkParent().cancelLessonInSingleSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("LkCancelLessonInSingleSchedule");
    app.postClean().dropTaskAndSchedule();
  }

  private void check(Schedules before, Schedules after) {
    app.trScheduleTomorrow()
        .SingleScheduleWithoutStudent(period, "LkCancelLessonInSingleSchedule", "14");

    ScheduleData scheduleAdd = scheduleService.findById("LkCancelLessonInSingleSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkCancelLessonInSingleSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
