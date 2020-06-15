package io.itgen.tests.requests;
//предполагается, что бд тестовая и нет расписаний на дефолтное время, следовательно можно оставить заявку

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RequestOnTrial2hScratchTests extends TestBase {
  String name = "Маша Машина";
  String idNewRequest = "";
  ArrayList<Comment> listcomment = new ArrayList<>();

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("makeRequest").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("makeRequest").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child", "donator"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("makeRequest").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
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
    studentService.findByIdAndDelete("makeRequest");
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("makeRequest");
    Tasks tasks = app.dbschedules().tasksComposition("makeRequest");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
    RequestService requestService = new RequestService();
    requestService.findByIdAndDelete(idNewRequest);

  }

  private void check(Requests before, Requests after) {
    RequestData requestAdd = new RequestData()
            .withId(idNewRequest)
            .withCreator("666") //суперадмин создал заявку (может быть привязаться к тому, кто залогинен)
            .withCreatorAt(new Date())
            .withStatus("open")
            .withChildId("makeRequest")
            .withComment(listcomment)
            .withActivity(Arrays.asList(new Activity().withUId("666").withTs(new Date()).withT("requestCreated")))
            .withSkill("1")
            .withDuration(2)
            .withPermanent(false)
            .withTrial(false)
            .withTimes(Arrays.asList(new Times().withMin(1592110800000.0).withMax(1592157600000.0),
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

