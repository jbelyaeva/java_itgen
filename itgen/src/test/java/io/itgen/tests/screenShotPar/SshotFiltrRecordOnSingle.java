package io.itgen.tests.screenShotPar;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.TimeGeneral;
import io.itgen.model.*;
import io.itgen.model.schedule.*;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Status;
import io.itgen.services.*;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class SshotFiltrRecordOnSingle extends TestBase {
  ArrayList<C> listC = new ArrayList<>();
  ArrayList<Slots> listSlots = new ArrayList<>();
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
                            .withId("LkRecordOnSingleSchedule")
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
            .withId("LkRecordOnSingleSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(listC)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(scheduleNew);

    //студент, добавленный в дефолтную семью, которыфй прошел пробное успешно
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("LkRecordOnSingleSchedule").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("BL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("111").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1263502800L))
            .withLangs(Arrays.asList("ru"))
            .withSkills(Arrays.asList("1"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("trialFinished"))
            .withLastSubjs(Arrays.asList("1")).withUsedSubjs(Arrays.asList("1"))
            .withFinishedLessonsCount(1)
            .withFinishedLessonsCountBySkill(new FinishedLessonsCountBySkill().withOne(1));
    studentService.save(student);

    //баланс +1, т.к. за 8 часов нельзя будет записаться через родителя
    PaymentService paymentService = new PaymentService();
    PaymentData payment = new PaymentData().withId("LkRecordOnSingleSchedule")
            .withCreateAt(new Date())
            .withfId("111").withCreator("666").withVal(1).withT(2).withDesc("корректировка")
            .withApproved(true);
    paymentService.save(payment);
  }

  @Test
  public void testFiltrRecordOnSingle() throws AWTException, IOException {
    app.lkParent().GoToFiltrRecordSingle();

    String name = "Parent_FiltrRecordOnSingle_RU_Chrome";
    String[] locatorIgnor = {
            "//p[@class='user']",
            "//div[@class='DayPickerInput']//input",
            "//span[@class='selected-icon']",
            "//div[contains(@id,'MeteorToys')]",
            "//div[@class='times-filter']"
    };

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    if (diff.getDiffSize() > 100) { //погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("FinishedSchedule");
    scheduleService.findByIdAndDelete("LkRecordOnSingleSchedule");

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("111").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LkRecordOnSingleSchedule");

    PaymentService paymentService = new PaymentService();
    paymentService.findByIdAndDelete("LkRecordOnSingleSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnSingleSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}
