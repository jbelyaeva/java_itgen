package tests.requests;
// предполагается, что бд тестовая и нет расписаний на дефолтное время, следовательно можно оставить
// заявку

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import data.model.family.FamilyData;
import data.model.users.StudentData;
import data.model.general.Activity;
import data.model.general.Comments;
import data.model.requests.RequestData;
import data.model.requests.Requests;
import data.model.requests.Times;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.services.FamilyService;
import data.services.RequestService;
import data.services.StudentService;
import data.services.TaskService;
import app.testbase.TestBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestOnTrial2hScratchTests extends TestBase {
  String name = "Маша Машина";
  String idNewRequest = "";
  ArrayList<Comments> listcomment = new ArrayList<>();

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("makeRequest").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("makeRequest")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child", "donator"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("makeRequest")
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
  public void testRequestOnTrial2hScratch() {
    app.goTo().menuSchedule();
    Requests before = app.dbrequest().allList();
    app.windowSchedule().makeRequestOnTrial2hScratch(name);
    Requests after = app.dbrequest().allList();
    assertThat(after.size(), equalTo(before.size() + 1));
    idNewRequest = app.request().getIdNewRequestDB(before, after);
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.DeleteById("makeRequest");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("makeRequest");
    Tasks tasks = app.dbschedules().tasksComposition("makeRequest");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
    RequestService requestService = new RequestService();
    requestService.DeleteById(idNewRequest);
  }

  private void check(Requests before, Requests after) {
    RequestData requestAdd =
        new RequestData()
            .withId(idNewRequest)
            .withCreator(
                "666") // суперадмин создал заявку (может быть привязаться к тому, кто залогинен)
            .withCreatorAt(new Date())
            .withStatus("open")
            .withChildId("makeRequest")
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

    for (RequestData requestBefore : before) {
      if (requestBefore.getId().equals(idNewRequest)) {
        assertThat(after, equalTo(before.without(requestBefore).withAdded(requestAdd)));
        return;
      }
    }
  }
}
