package io.itgen.tests.lkParent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.Schedules;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnRegular extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и постоянное расписание на завтра в 18.00, на которое нужно записать ученика
  @BeforeMethod
  public void ensurePreconditions() {

    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkRecordOnRegularSchedule", "1");

    app.trStudent()
        .StudentAddDefaultFamily_AfterTrial(
            "LkRecordOnRegularSchedule", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");

    app.trScheduleTomorrow()
        .RegularScheduleWithoutStudents(period, "LkRecordOnRegularSchedule", "14");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordOnRegular() {
    Schedules before = app.dbschedules().schedules();
    app.lkParent().recordOnRegular();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("FinishedSchedule");
    scheduleService.DeleteById("LkRecordOnRegularSchedule");
    studentService.DeleteById("LkRecordOnRegularSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnRegularSchedule");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    // завтра регулярное занятие, на которое записали ученика
    app.trScheduleTomorrow()
        .RegularScheduleWithOneOldStudent(
            period, "LkRecordOnRegularSchedule", "14", "LkRecordOnRegularSchedule", "1", "ru");

    ScheduleData scheduleAdd = scheduleService.findById("LkRecordOnRegularSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkRecordOnRegularSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
