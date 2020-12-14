package tests.lkParent;

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

public class RecordOnSingle extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и разовое расписание на завтра в 18.00, на которое нужно записать ученика
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkRecordOnSingleSchedule", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "LkRecordOnSingleSchedule",
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
            "trialFinished",
            "1",
            "1",
            1);

    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "LkRecordOnSingleSchedule", "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnSingle() {
    app.lkParent().btnLogo();
    Schedules before = app.dbschedules().schedules();
    app.lkParent().recordOnSingle();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("LkRecordOnSingleSchedule");
    app.postClean().dropTaskAndSchedule();
  }

  private void check(Schedules before, Schedules after) {

    app.trScheduleTomorrow()
        .SingleScheduleWithOneStudent(
            period, "LkRecordOnSingleSchedule", "14", "LkRecordOnSingleSchedule", "1", "ru");

    ScheduleData scheduleAdd = scheduleService.findById("LkRecordOnSingleSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkRecordOnRegularSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
