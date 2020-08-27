package io.itgen.tests.schedule;
//удаление ученика с постоянного двухчасового занятия
// Есть регулярное расписание на сегодня с 21-23. На него записан ученик на 2 ч платник. Отменить первое занятие

import io.itgen.general.TimeGeneral;
import io.itgen.model.*;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveStudentFromRegular2hSchedule extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  int week = 604800000;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("removeStudentFromLesson")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                            .withId("14")
                            .withW(time.date())
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3)))
                            .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period))),
                    new Slots().withId("14")
                            .withW(time.date() + week)
                            .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 2)
                            .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 3)
                            .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 4)
                            .withSt(new ST().withS(time.Stime(period) + week * 4).withE(time.Etime(period) + week * 4))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 5)
                            .withSt(new ST().withS(time.Stime(period) + week * 5).withE(time.Etime(period) + week * 5))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("removeStudentFromLesson").withTrialBonusOff(false).withTierId("txb");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("removeStudentFromLesson").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("removeStudentFromLesson").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("learning"));
    studentService.save(student);
  }

  @Test
  public void testRemoveStudentFromSingleSchedule() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().removeStudent("removeStudentFromLesson");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById("removeStudentFromLesson");
    StudentService studentService = new StudentService();
    studentService.DeleteById("removeStudentFromLesson");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("removeStudentFromLesson");
    Tasks tasks = app.dbschedules().tasksComposition("removeStudentFromLesson");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    TimeGeneral time = new TimeGeneral();
    ScheduleData scheduleAdd = new ScheduleData()
            .withId("removeStudentFromLesson")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                            .withId("14")
                            .withW(time.date())
                            .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                            .withC(list),
                    new Slots().withId("14")
                            .withW(time.date() + week)
                            .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 2)
                            .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 3)
                            .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 4)
                            .withSt(new ST().withS(time.Stime(period) + week * 4).withE(time.Etime(period) + week * 4))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3))),
                    new Slots().withId("14")
                            .withW(time.date() + week * 5)
                            .withSt(new ST().withS(time.Stime(period) + week * 5).withE(time.Etime(period) + week * 5))
                            .withC(Arrays.asList(new C().withId("removeStudentFromLesson").withType(3).withSubject("1")
                                    .withLang("ru").withNewSubj(true).withP(true).withS("normal").withScore(3)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("removeStudent")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }


}
