package io.itgen.tests.screenShot;

import io.itgen.model.*;
import io.itgen.model.requests.Activity;
import io.itgen.model.requests.Comment;
import io.itgen.model.requests.Times;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.RequestService;
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

public class SshotRequests extends TestBase {
  ArrayList<Comment> listcomment = new ArrayList<>();

  @BeforeMethod
  public void ensurePreconditions() {
    RequestService requestService = new RequestService();
    RequestData request = new RequestData()
            .withId("sshotRequests")
            .withCreator("666") //суперадмин создал заявку (может быть привязаться к тому, кто залогинен)
            .withCreatorAt(new Date())
            .withStatus("open")
            .withChildId("sshotRequests")
            .withComment(listcomment)
            .withActivity(Arrays.asList(new Activity().withUId("666").withTs(new Date()).withT("requestCreated")))
            .withSkill("1")
            .withDuration(2)
            .withPermanent(false)
            .withTrial(false)
            .withTimes(Arrays.asList(new Times().withMin(1592110800000.0).withMax(1592157600000.0),
                                     new Times().withMin(1592197200000.0).withMax(1592244000000.0),
                                     new Times().withMin(1592283600000.0).withMax(1592330400000.0)));

   requestService.save(request);

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("sshotRequests").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("sshotRequests").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("sshotRequests").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);

  }

  @Test
  public void testSshotRequests() throws AWTException, IOException {
    String name = "Admin_Requests_RU_Chrome";
    String[] locatorIgnor = null;

    app.goTo().menuTasks();
    app.goTo().menuRequests();

   ImageDiff diff = app.sshot().getImageDiff(properties.getProperty("expected")
            , properties.getProperty("actual")
            , properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    RequestService requestService = new RequestService();
    requestService.findByIdAndDelete("sshotRequests");
    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("sshotRequests");
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("sshotRequests");
    Tasks tasks = app.dbschedules().tasksComposition("sshotRequests");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}

