package tests.lkParent;
// отмена разового занятия

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelOnSingle extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера успешно
  // пробное в 18.00 и который записан на разовое расписание на завтра в 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkCancelLessonInSingleSchedule", "1");

    app.trStudent()
        .StudentAddDefaultFamilyAfterLesson(
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
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("FinishedSchedule");
    scheduleService.DeleteById("LkCancelLessonInSingleSchedule");
    studentService.DeleteById("LkCancelLessonInSingleSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkCancelLessonInSingleSchedule");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
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
