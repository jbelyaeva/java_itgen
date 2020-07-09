package io.itgen.tests.screenShot;
/**
 * Скриншот страницы с расписанием. База изначально должна быть пустая. Тест создает расписание,
 * делает снимок, сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка
 * запускаем конфигурацию запуска со свойством -Detalon=true.
 */
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
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static io.itgen.appmanager.ApplicationManager.properties;

public class SshotLessonWithStudent extends TestBase {
  String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule =
        new ScheduleData()
            .withId("SshotOnLessonWithStudent")
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
                                    .withId("SshotOnLessonWithStudent")
                                    .withType(1)
                                    .withSubject("1")
                                    .withLang("ru")
                                    .withNewSubj(true)
                                    .withS("normal")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);

    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData()
            .withId("SshotOnLessonWithStudent")
            .withTrialBonusOff(false)
            .withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("SshotOnLessonWithStudent")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("SshotOnLessonWithStudent")
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

  @Test
  public void testSshotLessonWithStudent() throws AWTException, IOException {
    String name = "Admin_ScheduleWithStudent_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='text-capitalize'][2]"));
    locatorIgnor.add(By.xpath("//p"));

    String[] deleteElements={
        "//p[contains(@class,'start-info')]"
    };

    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.schedule().selectScheduleInListUIById("SshotOnLessonWithStudent");
    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                properties.getProperty("expected"),
                properties.getProperty("actual"),
                properties.getProperty("markedImages"),
                name,
                locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("SshotOnLessonWithStudent");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("SshotOnLessonWithStudent");

    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("SshotOnLessonWithStudent");

    Tasks tasks = app.dbschedules().tasksComposition("SshotOnLessonWithStudent");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}
