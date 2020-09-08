package io.itgen.tests.scheduleWindow;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.Schedules;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.FamilyService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowRecordStudentOnSingleSecond1hScheduleTests extends TestBase {

  String period = "18:00 - 20:00";
  String name = "Маша Машина";
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "recordOnSchedule", "14");
    app.trFamily().newFamily("recordStudent", false, "txa");

    app.trStudent()
        .newStudent(
            "recordStudent",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "recordStudent");
  }

  @Test
  public void testWindowRecordStudentOnSingleSecond1h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOnSingleSecond1h(name, "14");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    // проверка, что назначен новый тренер и остальные записи не изменились
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("recordOnSchedule");
    studentService.DeleteById("recordStudent");
    familyService.DeleteById("recordStudent");
    Tasks tasks = app.dbschedules().tasksComposition("recordStudent");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    app.trScheduleTomorrow()
        .CombinationWithOneStudentOnSingleScredule_2(
            period, "recordOnSchedule", "14", "recordStudent", "1", "ru");
    ScheduleData scheduleAdd = scheduleService.findById("recordOnSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("recordOnSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
