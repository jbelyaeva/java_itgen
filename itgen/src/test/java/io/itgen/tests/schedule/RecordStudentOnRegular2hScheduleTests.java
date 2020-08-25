package io.itgen.tests.schedule;
// автотест проверяет назначение другого тренера (c id=18) в постоянном расписании на одно занятие и
// на все
// начальные данные: период, id тренера

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.TimeGeneral;
import io.itgen.model.FamilyData;
import io.itgen.model.ScheduleData;
import io.itgen.model.Schedules;
import io.itgen.model.StudentData;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordStudentOnRegular2hScheduleTests extends TestBase {
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();
  TimeGeneral time = new TimeGeneral();

  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  int week = 604800000;
  String name = "Маша Машина";

  @BeforeMethod
  public void ensurePreconditions() {

    ScheduleData schedule =
        new ScheduleData()
            .withId("recordStudentOnLesson")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week)
                                .withE(time.Etime(period) + week))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 2)
                                .withE(time.Etime(period) + week * 2))
                        .withC(list),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 3)
                                .withE(time.Etime(period) + week * 3))
                        .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);

    FamilyData family =
        new FamilyData().withId("recordStudent").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentData student =
        new StudentData()
            .withId("recordStudent")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("recordStudent")
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

  @Test
  public void testRecordStudentOnRegular2h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOn2h(name, "recordStudentOnLesson");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(
        before,
        after); // проверка на то, что новая запись записалась в бд верно, и остальные записи не
                // испортились
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("recordStudentOnLesson");
    studentService.DeleteById("recordStudent");
    familyService.DeleteById("recordStudent");
    Tasks tasks = app.dbschedules().tasksComposition("recordStudent");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    ScheduleData scheduleAdd =
        new ScheduleData()
            .withId("recordStudentOnLesson")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14") // 18
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId("recordStudent")
                                    .withType(3)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withNewSubj(true)
                                    .withP(true))),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week)
                                .withE(time.Etime(period) + week))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId("recordStudent")
                                    .withType(3)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withP(true))),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 2)
                                .withE(time.Etime(period) + week * 2))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId("recordStudent")
                                    .withType(3)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withP(true))),
                    new Slots()
                        .withId("14")
                        .withW(time.date() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 3)
                                .withE(time.Etime(period) + week * 3))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId("recordStudent")
                                    .withType(3)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withP(true)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("recordStudentOnLesson")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
