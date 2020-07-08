package io.itgen.tests.lkParent;
// к дефолтному родителю и ученику добавляется еще ученик, которого запишем на разовое и затем
// удалим этого ученика и расписание в after-методе

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.TimeGeneral;
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

public class RecordOnSingle extends TestBase {
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и разовое расписание на завтра в 18.00, на которое нужно записать ученика
  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();

    // первое пробное занятие, которое вчера завершил ученик с Был
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            time,
            scheduleService,
            period,
            "FinishedSchedule",
            "14",
            "LkRecordOnSingleSchedule",
            "1");

    // занятие, на которое нужно записать ученика
    app.trScheduleTomorrow()
        .SingleScheduleWithoutStudent(
            time, scheduleService, period, "LkRecordOnSingleSchedule", "14");

    // студент, добавленный в дефолтную семью, который прошел пробное успешно
    StudentService studentService = new StudentService();
    app.trStudent()
        .StudentAddDefaultFamily_AfterTrial(
            studentService,
            "LkRecordOnSingleSchedule",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            "ru",
            "ru");
  }

  @Test()
  public void testRecordOnSingle() {
    Schedules before = app.dbschedules().schedules();
    app.lkParent().recordOnSingle();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("FinishedSchedule");
    scheduleService.findByIdAndDelete("LkRecordOnSingleSchedule");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LkRecordOnSingleSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnSingleSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();

    app.trScheduleTomorrow()
        .SingleScheduleWithOneStudent(
            time,
            scheduleService,
            period,
            "LkRecordOnSingleSchedule",
            "14",
            "LkRecordOnSingleSchedule",
            "1",
            "ru");

    ScheduleData scheduleAdd = scheduleService.findById("LkRecordOnSingleSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkRecordOnRegularSchedule")) {
        Schedules befor11 = (before.without(scheduleBefore).withAdded(scheduleAdd));
        assertThat(after, equalTo(befor11));
        return;
      }
    }
  }
}
