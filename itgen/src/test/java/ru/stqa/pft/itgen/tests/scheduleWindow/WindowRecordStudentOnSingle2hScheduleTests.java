package ru.stqa.pft.itgen.tests.scheduleWindow;
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

public class WindowRecordStudentOnSingle2hScheduleTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "18:00 - 20:00";
  int week = 604800000;
  String name = "Маша Машина";
  ScheduleData schedule = null;

  @BeforeMethod
  public void ensurePreconditions() {
    //Начальные данные: есть ученик и расписание, на которое надо записать этого ученика
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    schedule = new ScheduleData()
            .withId("recordOnSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1").withOneTime(true);
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
  public void testWindowRecordStudentOnSingle2h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOn2hSingle(name,schedule.getSlots().get(0).getId()); //имя ученика для поиска, id тренера
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("recordOnSchedule");
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
            .withId("recordOnSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(Arrays.asList(new C().withId("recordStudent").withType(3).withSubject("1")
                            .withLang("ru").withNewSubj(true).withS("normal")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1").withOneTime(true);

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("recordOnSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }

}
