package tests.schedule;
// автотест проверяет назначение другого тренера (c id=18) в постоянном расписании на одно занятие и
// на все
// начальные данные: период, id тренера

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.family.FamilyData;
import data.model.schedule.C;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.model.users.StudentData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.services.FamilyService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordStudentOnSingleSecond1hScheduleTests extends TestBase {
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();
  TimeGeneral time = new TimeGeneral();

  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  String name = "Маша Машина";

  @BeforeMethod
  public void ensurePreconditions() {
    ScheduleData schedule =
        new ScheduleData()
            .withId("recordOnSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);
    scheduleService.save(schedule);

    FamilyData family =
        new FamilyData().withId("recordOnSchedule").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentData student =
        new StudentData()
            .withId("recordOnSchedule")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("recordOnSchedule")
            .withStudyLang("ru")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordStudentOnSingleSecond1h() throws InterruptedException {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOnSecond1h(name, "recordOnSchedule");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    // проверка на то, что новая запись записалась в бд верно, и остальные записи не испортились
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("recordOnSchedule");
    studentService.DeleteById("recordOnSchedule");
    familyService.DeleteById("recordOnSchedule");
    Tasks tasks = app.dbschedules().tasksComposition("recordOnSchedule");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    ScheduleData scheduleAdd =
        new ScheduleData()
            .withId("recordOnSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId("recordOnSchedule")
                                    .withType(2)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withNewSubj(true)
                                    .withS("normal")
                                    .withKind("oneTime")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("recordOnSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
