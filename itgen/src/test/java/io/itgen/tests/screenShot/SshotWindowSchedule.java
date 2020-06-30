package io.itgen.tests.screenShot;

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

import static io.itgen.appmanager.ApplicationManager.properties;

public class SshotWindowSchedule extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  String nameStudent = "Маша Машина";
  ScheduleData schedule = null;

  @BeforeMethod
  public void ensurePreconditions() {
    //Начальные данные: есть ученик и расписание, на которое надо записать этого ученика
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    schedule = new ScheduleData()
            .withId("sshotWindowSchedule")
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
    FamilyData family = new FamilyData().withId("sshotWindowSchedule").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("sshotWindowSchedule").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("sshotWindowSchedule").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);

  }


  @Test
  public void testSshotWindowSchedule() throws AWTException, IOException {
    String name = "Admin_WindowSchedule_RU_Chrome";
    String[] locatorIgnor = {
            "//span[contains(@class,'capitalize')]"
    };

    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.windowSchedule().selectStudentForSshot(nameStudent);
    app.sshot().changeTopBar();
    app.sshot().changeTableInWindowSchedule();

   ImageDiff diff = app.sshot().getImageDiff(properties.getProperty("expected")
            , properties.getProperty("actual")
            , properties.getProperty("markedImages")
            , name, locatorIgnor);
    if (diff.getDiffSize() > 250) { //погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("sshotWindowSchedule");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("sshotWindowSchedule");

    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("sshotWindowSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("sshotWindowSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}

