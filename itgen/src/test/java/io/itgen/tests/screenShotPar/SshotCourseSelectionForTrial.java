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

public class SshotCourseSelectionForTrial extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  ArrayList<FinishedSlots> listF = new ArrayList<>();
  String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("LKOnTrail")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list)))
            .withFinishedSlots(listF)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1").withOneTime(true);
    scheduleService.save(schedule);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("LKOnTrail").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("111").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1263502800L))
            .withLangs(Arrays.asList("ru"))
            .withSkills(Arrays.asList("1"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }

  @Test
  public void testSshotCourseSelectionForTrial() throws AWTException, IOException {
    app.lkParent().btnRecordOnTrail();

    String name = "Parent_CourseSelectionForTrial_RU_Chrome";
    String[] locatorIgnor = {
            "//p[@class='user']",
            "//div[contains(@id,'MeteorToys')]"
    };

    app.sshot().changeTopBar();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 100) { //погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("LKOnTrail");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LKOnTrail");

    Tasks tasks = app.dbschedules().tasksComposition("LKOnTrail");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}
