package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет назначение другого тренера (c id=18) в постоянном расписании на одно занятие и на все
//начальные данные: период, id тренера

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

public class RecordStudentOnRegular2hScheduleTests extends TestBase {
  ArrayList<C> list = new ArrayList();
  String period="21:00 - 23:00";
  int week=604800000;
  String name="Маша Машина";

  @BeforeMethod
  public void ensurePreconditions() {
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("recordStudentOnLesson")
            .withVer(0)
            .withFromDate(app.time().time(period))
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(app.time().time(period))
                    .withSt(new ST().withS(app.time().Stime(period)).withE(app.time().Etime(period)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week))
                    .withSt(new ST().withS(app.time().Stime(period+week)).withE(app.time().Etime(period+week)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*2))
                    .withSt(new ST().withS(app.time().Stime(period+week*2)).withE(app.time().Etime(period+week*2)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*3))
                    .withSt(new ST().withS(app.time().Stime(period+week*3)).withE(app.time().Etime(period+week*3)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*4))
                    .withSt(new ST().withS(app.time().Stime(period+week*4)).withE(app.time().Etime(period+week*4)))
                    .withC(list),new Slots()
                    .withId("14")
                    .withW(app.time().time(period+week*5))
                    .withSt(new ST().withS(app.time().Stime(period+week*5)).withE(app.time().Etime(period+week*5)))
                    .withC(list)))
            .withTimes(new Times().withStart(app.time().start(period)).withEnd(app.time().finish(period)))
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
  public void testRecordStudentOnRegular2h() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().recordStudentOnRegular2h(name,"recordStudentOnLesson");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    //проверка, что назначен новый тренер и остальные записи не изменились
    check(before, after);
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
    for (TaskData taskClean: tasks) {
       taskService.findByIdAndDelete(taskClean.getId());
    }
   }

  private void check(Schedules before, Schedules after) {
    ScheduleData scheduleAdd = new ScheduleData()
            .withId("recordStudentOnLesson")
            .withVer(0)
            .withFromDate(app.time().time(period))
            .withSlots(Arrays.asList(new Slots()
                    .withId("18")
                    .withW(app.time().time(period))
                    .withSt(new ST().withS(app.time().Stime(period)).withE(app.time().Etime(period)))
                    .withC(Arrays.asList(new C().withId("recordStudent").withType(3).withSubject("1")
                                                .withLang("ru").withNewSubj(true))),
                     new Slots().withId("14")
                    .withW(app.time().time(period+week))
                    .withSt(new ST().withS(app.time().Stime(period+week)).withE(app.time().Etime(period+week)))
                    .withC(Arrays.asList(new C().withId("recordStudent").withType(3).withSubject("1")
                                     .withLang("ru").withNewSubj(true))),
                    new Slots().withId("14")
                    .withW(app.time().time(period+week*2))
                    .withSt(new ST().withS(app.time().Stime(period+week*2)).withE(app.time().Etime(period+week*2)))
                    .withC(Arrays.asList(new C().withId("recordStudent").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true))),
                     new Slots().withId("14")
                    .withW(app.time().time(period+week*3))
                    .withSt(new ST().withS(app.time().Stime(period+week*3)).withE(app.time().Etime(period+week*3)))
                    .withC(Arrays.asList(new C().withId("recordStudent").withType(3).withSubject("1")
                                     .withLang("ru").withNewSubj(true))),
                    new Slots().withId("14")
                    .withW(app.time().time(period+week*4))
                    .withSt(new ST().withS(app.time().Stime(period+week*4)).withE(app.time().Etime(period+week*4)))
                    .withC(Arrays.asList(new C().withId("recordStudent").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true))),
                    new Slots().withId("14")
                    .withW(app.time().time(period+week*5))
                    .withSt(new ST().withS(app.time().Stime(period+week*5)).withE(app.time().Etime(period+week*5)))
                    .withC(Arrays.asList(new C().withId("recordStudent").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true)))))
            .withTimes(new Times().withStart(app.time().start(period)).withEnd(app.time().finish(period)))
            .withSkypeId("1");

    for (ScheduleData scheduleBefore : before) { //найти в списке "до" родителя с таким id
      if (scheduleBefore.getId().equals("scheduleRegularAssignTrainer")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }

}

