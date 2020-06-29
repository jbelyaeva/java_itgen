package io.itgen.tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
   сравнивает его с эталонным.
 */


import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.TimeGeneral;
import io.itgen.model.ScheduleData;
import io.itgen.model.StudentData;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.model.schedule.*;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Status;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
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

public class SshotConfirmRecordOnRegular extends TestBase {
  ArrayList<C> listC = new ArrayList<>();
  ArrayList<Slots> listSlots = new ArrayList<>();
  String periodFinish = "01:00 - 03:00";
  String period = "21:00 - 23:00";
  int week = 604800000;

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
                    .withC(Arrays.asList(new C().withId("LkRecordOnRegularSchedule").withType(3).withSubject("1")
                            .withLang("ru").withTrial(true).withS("finished").withScore(3).withRating(4)))
                    .withStartedAt(time.StimeYesterday(periodFinish)).withFinishedAt(time.EtimeYesterday(periodFinish))))
            .withTimes(new Times().withStart(time.start(periodFinish)).withEnd(time.finish(periodFinish)))
            .withSkypeId("1").withOneTime(true);
    scheduleService.save(schedule);
    //занятие, на которое нужно записать ученика
    ScheduleData scheduleNew = new ScheduleData()
            .withId("LkRecordOnRegularSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(listC), new Slots()
                    .withId("14")
                    .withW(time.date() + week)
                    .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                    .withC(listC), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 2)
                    .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                    .withC(listC), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 3)
                    .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                    .withC(listC)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(scheduleNew);
    //студент, добавленный в дефолтную семью, которыфй прошел пробное успешно
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("LkRecordOnRegularSchedule").withFirstName("Маша").withLastName("Машина")
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
  }

  @Test
  public void testConfirmRecordOnRegular() throws AWTException, IOException {
   app.lkParent().confirmRecordOnRegular();

    String name = "Parent_ConfirmRecordOnRegular_RU_Chrome";
    String[] locatorIgnor = {
            "//p[@class='user']",
            "//div[@class='DayPickerInput']//input",
            "//span[@class='selected-icon']",
            "//div[contains(@id,'MeteorToys')]",
            "//strong",
            "//div[contains(@id,'MeteorToys')]"
    };

    app.sshot().changeStyleDayOfTheWeek();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);

    app.lkParent().btnRecord();
    app.lkParent().btnLogo();
   }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("FinishedSchedule");
    scheduleService.findByIdAndDelete("LkRecordOnRegularSchedule");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LkRecordOnRegularSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnRegularSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}