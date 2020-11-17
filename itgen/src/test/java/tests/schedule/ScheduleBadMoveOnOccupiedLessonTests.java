package tests.schedule;
// автотест проверяет подвижку разового расписания

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.family.FamilyData;
import data.model.users.StudentData;
import data.model.schedule.C;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.services.FamilyService;
import data.services.ScheduleService;
import data.services.StudentService;
import app.testbase.TestBase;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleBadMoveOnOccupiedLessonTests extends TestBase {
  String period = "18:00 - 20:00";
  String periodMove = "21:00 - 23:00";
  Schedules after = null;
  Schedules before = null;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    // расписание, на которое записан ученик, его будем двигать на время расписания ниже
    ScheduleData schedule =
        new ScheduleData()
            .withId("badMoveSchedule")
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
                                    .withId("badMoveSchedule")
                                    .withType(3)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withNewSubj(true)
                                    .withS("normal")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
    // расписание, на которое ученик уже записан
    ScheduleData scheduleOccupied =
        new ScheduleData()
            .withId("scheduleOccupied")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(
                            new ST().withS(time.Stime(periodMove)).withE(time.Etime(periodMove)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId("badMoveSchedule")
                                    .withType(3)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withNewSubj(true)
                                    .withS("normal")))))
            .withTimes(
                new Times().withStart(time.start(periodMove)).withEnd(time.finish(periodMove)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(scheduleOccupied);

    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("badMoveSchedule").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("badMoveSchedule")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("badMoveSchedule")
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
  public void testBadMoveOnOccupiedLesson() {
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().badMoveOccupied(periodMove, "badMoveSchedule");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    // проверка, что в списке недоступных студентов появился студент из предусловия
    app.schedule().checkFindBusyStuden("badMoveSchedule");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById("badMoveSchedule");
    scheduleService.DeleteById("scheduleOccupied");
    StudentService studentService = new StudentService();
    studentService.DeleteById("badMoveSchedule");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("badMoveSchedule");
  }
}
