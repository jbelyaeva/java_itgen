package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет назначение другого тренера (c id=18) в постоянном расписании на одно занятие и на все
//начальные данные: период, id тренера

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.general.TimeGeneral;
import ru.stqa.pft.itgen.model.*;
import ru.stqa.pft.itgen.model.Schedule.C;
import ru.stqa.pft.itgen.model.Schedule.ST;
import ru.stqa.pft.itgen.model.Schedule.Slots;
import ru.stqa.pft.itgen.model.Schedule.Times;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Status;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.services.StudentService;
import ru.stqa.pft.itgen.services.TaskService;
import ru.stqa.pft.itgen.tests.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecordStudentOnRegularSecond1hScheduleTests extends TestBase {
  ArrayList<C> list = new ArrayList();
  String period = "21:00 - 23:00";
  int week = 604800000;
  String name = "Маша Машина";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("recordStudentOnLesson")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week)
                    .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 2)
                    .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 3)
                    .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("recordStudent").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("recordStudent").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("recordStudent").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);

  }

  @Test
  public void testRecordStudentOnRegularSecond1h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOnSecond1h(name, "recordStudentOnLesson");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    //проверка, что назначен новый тренер и остальные записи не изменились
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("recordStudentOnLesson");
    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("recordStudent");
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("recordStudent");
    Tasks tasks = app.dbschedules().tasksComposition("recordStudent");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    TimeGeneral time = new TimeGeneral();
    ScheduleData scheduleAdd = new ScheduleData()
            .withId("recordStudentOnLesson")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                            .withId("14") //18
                            .withW(time.date())
                            .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                            .withC(Arrays.asList(new C().withId("recordStudent").withType(2).withSubject("1")
                                    .withLang("ru").withNewSubj(true))),
                    new Slots().withId("14")
                            .withW(time.date() + week)
                            .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                            .withC(Arrays.asList(new C().withId("recordStudent").withType(2).withSubject("1")
                                    .withLang("ru").withNewSubj(true))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 2)
                            .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                            .withC(Arrays.asList(new C().withId("recordStudent").withType(2).withSubject("1")
                                    .withLang("ru").withNewSubj(true))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 3)
                            .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                            .withC(Arrays.asList(new C().withId("recordStudent").withType(2).withSubject("1")
                                    .withLang("ru").withNewSubj(true)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");

    for (ScheduleData scheduleBefore : before) { //найти в списке "до" родителя с таким id
      if (scheduleBefore.getId().equals("scheduleRegularAssignTrainer")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }

}
