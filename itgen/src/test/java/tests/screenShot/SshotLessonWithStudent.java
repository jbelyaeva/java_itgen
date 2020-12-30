package tests.screenShot;
/**
 * Скриншот страницы с расписанием. База изначально должна быть пустая. Тест создает расписание,
 * делает снимок, сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка
 * запускаем конфигурацию запуска со свойством -Detalon=true.
 */

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.TimeGeneral;
import data.model.family.FamilyData;
import data.model.schedule.C;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.model.users.StudentData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.services.FamilyService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

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
                                    .withS("normal")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))
                                    .withKind("oneTime")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
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

    String[] deleteElements = {
      "//p[contains(@class,'start-info')]", "//div[@class='text-capitalize'][2]"
    };

    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.schedule().selectScheduleInListUIById("SshotOnLessonWithStudent");
    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById("SshotOnLessonWithStudent");

    StudentService studentService = new StudentService();
    studentService.DeleteById("SshotOnLessonWithStudent");

    FamilyService familyService = new FamilyService();
    familyService.DeleteById("SshotOnLessonWithStudent");

    Tasks tasks = app.dbschedules().tasksComposition("SshotOnLessonWithStudent");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
