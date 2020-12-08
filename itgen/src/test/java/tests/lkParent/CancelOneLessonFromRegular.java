package tests.lkParent;
// отмена ученику одного занятия из постоянного расписания

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOneLessonFromRegular extends TestBase {

  StudentService studentService = new StudentService();
  ScheduleService scheduleService = new ScheduleService();
  TaskService taskService = new TaskService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и который записан на постоянное расписание на завтра в 18.00
  @BeforeMethod
  public void ensurePreconditions() {

    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkCancelRegularSchedule", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "LkCancelRegularSchedule",
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
        .RegularScheduleWithOneOldStudent(
            period, "LkCancelRegularSchedule", "14", "LkCancelRegularSchedule", "1", "ru");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCancelOneLessonFromRegular() {
    app.lkParent().btnLogo();
    Schedules before = app.dbschedules().schedules();
    app.lkParent().cancelOneLessonInRegularSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
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

    // регулярное расписание на завтра с учеником, первое занятие отменено
    app.trScheduleTomorrow()
        .RegularScheduleWithoutStudentOnFirstLesson(
            period, "LkCancelRegularSchedule", "14", "LkCancelRegularSchedule", "1", "ru");

    ScheduleData scheduleAdd = scheduleService.findById("LkCancelRegularSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkCancelRegularSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
