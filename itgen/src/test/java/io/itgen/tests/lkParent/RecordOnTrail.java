package io.itgen.tests.lkParent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

public class RecordOnTrail extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик
  // и разовое расписание на завтра в 18.00, на которое нужно записать добавленного ученика
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "LKOnTrail", "14");

    app.trStudent()
        .StudentAddDefaultFamily("LKOnTrail", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test()
  public void testRecordOnTrail() throws InterruptedException {
    Schedules before = app.dbschedules().schedules();
    app.lkParent().RecordOnTrail();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("LKOnTrail");
    studentService.DeleteById("LKOnTrail");

    Tasks tasks = app.dbschedules().tasksComposition("LKOnTrail");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
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
