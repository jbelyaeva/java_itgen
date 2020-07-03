package io.itgen.tests.lkParent;
// к дефолтному родителю и ученику добавляется еще ученик, которого запишем на разовое и затем удалим этого ученика
//и расписание в after-методе

import io.itgen.general.TimeGeneral;
import io.itgen.model.*;
import io.itgen.model.schedule.*;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Status;
import io.itgen.services.PaymentService;
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

public class CancelOnSingle extends TestBase {
  ArrayList<C> listC = new ArrayList<>();
  ArrayList<Slots> listSlots = new ArrayList<>();
  ArrayList<FinishedSlots> listFSlots = new ArrayList<>();
  String periodFinish = "01:00 - 03:00";
  String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    //занятие, которое ученик закончил
    ScheduleData schedule = new ScheduleData()
            .withId("FinishedSchedule")
            .withVer(0)
            .withFromDate(time.dateYesterday())
            .withSlots(listSlots)
            .withFinishedSlots(Arrays.asList(new FinishedSlots()
                    .withId("14")
                    .withW(time.dateYesterday())
                    .withSt(new ST().withS(time.StimeYesterday(periodFinish)).withE(time.EtimeYesterday(periodFinish)))
                    .withC(Arrays.asList(new C()
                            .withId("LkCancelLessonInSingleSchedule")
                            .withType(3)
                            .withSubject("1")
                            .withLang("ru")
                            .withTrial(true)
                            .withS("finished")
                            .withScore(3)
                            .withRating(4)))
                    .withStartedAt(time.StimeYesterday(periodFinish)).withFinishedAt(time.EtimeYesterday(periodFinish))))
            .withTimes(new Times().withStart(time.start(periodFinish)).withEnd(time.finish(periodFinish)))
            .withSkypeId("1").withOneTime(true);
    scheduleService.save(schedule);
    //занятие, на которое нужно записать ученика
    ScheduleData scheduleNew = new ScheduleData()
            .withId("LkCancelLessonInSingleSchedule")
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14") //18
                    .withW(time.dateTomorrow())
                    .withSt(new ST().withS(time.StimeTomorrow(period)).withE(time.EtimeTomorrow(period)))
                    .withC(Arrays.asList(new C().withId("LkCancelLessonInSingleSchedule").withType(3).withSubject("1")
                            .withLang("ru").withS("normal").withScore(3)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(scheduleNew);
    //студент, добавленный в дефолтную семью, которыфй прошел пробное успешно
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("LkCancelLessonInSingleSchedule").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("BL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("111").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1263502800L))
            .withLangs(Arrays.asList("ru"))
            .withSkills(Arrays.asList("1"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("learning"))
            .withLastSubjs(Arrays.asList("1")).withUsedSubjs(Arrays.asList("1"))
            .withFinishedLessonsCount(1)
            .withFinishedLessonsCountBySkill(new FinishedLessonsCountBySkill().withOne(1));
    studentService.save(student);

  }

  @Test()
  public void testRecordOnSingle() {
    Schedules before = app.dbschedules().schedules();
    app.lkParent().cancelLessonInSingleSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("FinishedSchedule");
    scheduleService.findByIdAndDelete("LkCancelLessonInSingleSchedule");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LkCancelLessonInSingleSchedule");

    PaymentService paymentService = new PaymentService();
    paymentService.findByIdAndDelete("LkCancelLessonInSingleSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkCancelLessonInSingleSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    TimeGeneral time = new TimeGeneral();
    ScheduleData scheduleAdd = new ScheduleData()
            .withId("LkCancelLessonInSingleSchedule")
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.dateTomorrow())
                    .withSt(new ST().withS(time.StimeTomorrow(period)).withE(time.EtimeTomorrow(period)))
                    .withC(listC)))
            .withFinishedSlots(listFSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkCancelLessonInSingleSchedule")) {
        Schedules befor11 = (before.without(scheduleBefore).withAdded(scheduleAdd));
        assertThat(after, equalTo(befor11));
        return;
      }
    }
  }

}
