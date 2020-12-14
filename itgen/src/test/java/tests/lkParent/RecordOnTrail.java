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

public class RecordOnTrail extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик
  // и разовое расписание на завтра в 18.00, на которое нужно записать добавленного ученика
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "LKOnTrail", "14");

    app.trStudent()
        .newStudent(
            "LKOnTrail",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial"
        );
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnTrail() {
    Schedules before = app.dbschedules().schedules();
    app.lkParent().recordOnTrail(1);
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("LKOnTrail");
    app.postClean().dropTaskAndSchedule();
  }

  private void check(Schedules before, Schedules after) {
    app.trScheduleTomorrow()
        .SingleScheduleWithOneStudentRecordOnTrail(
            period, "LKOnTrail", "14", "LKOnTrail", "1", "ru");

    ScheduleData scheduleAdd = scheduleService.findById("LKOnTrail");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LKOnTrail")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
