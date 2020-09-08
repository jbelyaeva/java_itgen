package io.itgen.tests.scheduleWindow;
//автотест проверяет запись платника на пробное занятие в постоянном расписании
//проверяет, что переключатель Пробное стоит по дефолту
//начальные данные: период, id тренера

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.TimeGeneral;
import io.itgen.model.FamilyData;
import io.itgen.model.ScheduleData;
import io.itgen.model.Schedules;
import io.itgen.model.StudentData;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowTest extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "18:00 - 20:00";
  int week = 604800000;
  String name = "Маша Машина";
  ScheduleData schedule = null;

  @BeforeMethod
  public void ensurePreconditions() {

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
  public void testWindow() {
    app.goTo().menuSchedule();
    app.schedule().createSingleScheduleTest();
    app.windowSchedule().recordStudentOnTrial(name,"14"); //имя ученика, id тренера
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.drop();
    StudentService studentService = new StudentService();
    studentService.DeleteById("recordStudent");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("recordStudent");
    Tasks tasks = app.dbschedules().tasksComposition("recordStudent");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}

