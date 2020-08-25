package io.itgen.tests.lkParent;
// отмена ученику все занятия из постоянного расписания

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.ScheduleData;
import io.itgen.model.Schedules;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOnRegular extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и который записан на постоянное расписание на завтра в 18.00
  @BeforeMethod
  public void ensurePreconditions() {

    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkCancelRegularSchedule", "1");

    app.trStudent()
        .StudentAddDefaultFamily_FinishedTrailLesson_RecordSingle(
            "LkCancelRegularSchedule", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");

    app.trScheduleTomorrow()
        .RegularScheduleWithOneOldStudent(
            period, "LkCancelRegularSchedule", "14", "LkCancelRegularSchedule", "1", "ru");
  }

  @Test
  public void testCancelOnRegular() {
    Schedules before = app.dbschedules().schedules();
    app.lkParent().cancelLessonsInRegularSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("FinishedSchedule");
    scheduleService.DeleteById("LkCancelRegularSchedule");
    studentService.DeleteById("LkCancelRegularSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkCancelRegularSchedule");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {

    // регулярное занятие на завтра без учеников
    app.trScheduleTomorrow()
        .RegularScheduleWithoutStudents(period, "LkCancelRegularSchedule", "14");

    ScheduleData scheduleAdd = scheduleService.findById("LkCancelRegularSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkCancelRegularSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
