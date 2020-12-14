package tests.screenShot;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.model.family.FamilyData;
import data.model.general.Activity;
import data.model.general.Comments;
import data.model.requests.RequestData;
import data.model.requests.Times;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.model.users.StudentData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.services.FamilyService;
import data.services.RequestService;
import data.services.StudentService;
import data.services.TaskService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
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

public class SshotRequests extends TestBase {
  ArrayList<Comments> listcomment = new ArrayList<>();

  @BeforeMethod
  public void ensurePreconditions() {
    RequestService requestService = new RequestService();
    RequestData request =
        new RequestData()
            .withId("sshotRequests")
            .withCreator(
                "666") // суперадмин создал заявку (может быть привязаться к тому, кто залогинен)
            .withCreatorAt(new Date())
            .withStatus("open")
            .withChildId("sshotRequests")
            .withComments(listcomment)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId("666").withTs(new Date()).withT("requestCreated")))
            .withSkill("1")
            .withDuration(2)
            .withPermanent(false)
            .withTrial(false)
            .withTimes(
                Arrays.asList(
                    new Times().withMin(1592110800000.0).withMax(1592157600000.0),
                    new Times().withMin(1592197200000.0).withMax(1592244000000.0),
                    new Times().withMin(1592283600000.0).withMax(1592330400000.0)));
    requestService.save(request);

    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("sshotRequests").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("sshotRequests")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("sshotRequests")
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
  public void testSshotRequests() throws AWTException, IOException {
    String name = "Admin_Requests_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.goTo().menuRequests();

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
    RequestService requestService = new RequestService();
    requestService.DeleteById("sshotRequests");

    StudentService studentService = new StudentService();
    studentService.DeleteById("sshotRequests");

    FamilyService familyService = new FamilyService();
    familyService.DeleteById("sshotRequests");

    Tasks tasks = app.dbschedules().tasksComposition("sshotRequests");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
