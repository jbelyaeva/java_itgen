package tests.scheduleWindow;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.FamilyService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowRecordStudentOnSingleFirst1hScheduleTests extends TestBase {

  String period = "18:00 - 20:00";
  String name = "Маша Машина";
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "recordOnSchedule", "14");
    app.trFamily().newFamily("recordStudent", false, "txc");

    app.trStudent()
        .newStudent(
            "recordStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "recordStudent",
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

  @Test
  public void testWindowRecordStudentOnRegularFirst1h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOnSingleFirst1h(name, "14");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    // проверка, что назначен новый тренер и остальные записи не изменились
    check(before, after);
    app.check().findElement(By.xpath("//button[contains(@class,'cancel')]"));
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
        .CombinationWithOneStudentOnSingleScredule_1(
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
