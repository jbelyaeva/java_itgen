package io.itgen.tests.selfRegistration;
/*кейс: Нужно добавить родителя лида под админом, взять его id, вставить в ссылку, заполнить форму, получить письмо
 * активировать его, разлогиниться из-под родителя*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.Families;
import io.itgen.model.FamilyData;
import io.itgen.model.Leads;
import io.itgen.model.ParentData;
import io.itgen.model.Parents;
import io.itgen.model.StudentData;
import io.itgen.model.Students;
import io.itgen.services.FamilyService;
import io.itgen.services.LeadService;
import io.itgen.services.ParentService;
import io.itgen.services.ScheduleService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SelfRegistration extends TestBase {
  LeadService leadService = new LeadService();
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  ParentService parentService = new ParentService();
  FamilyService familyService = new FamilyService();

  StudentData studentClean = null;
  ParentData parentClean = null;
  FamilyData familyClean = null;

  @BeforeMethod
  public void ensurePreconditions() {

    app.trLead()
        .leadParent(
            "selfRegistration",
            "Лид",
            "Лидов",
            "parent",
            "BY",
            "Europe/Minsk",
            "ru",
            "+7859561122",
            "mail@list.ru",
            "new",
            "site",
            "manual");
  }

  @DataProvider
  public Iterator<Object[]> validLeadsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/self_registration.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(json, new TypeToken<List<StudentData>>() {}.getType());
      return students.stream().map((p) -> new Object[] {p}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validLeadsFromJson")
  public void testSelfRegistration(StudentData student)
      throws MessagingException, InterruptedException {

    app.student().logout();
    Leads leadsBefore = app.db().leads();
    Students studentsBefore = app.dbstudents().students();
    Parents parentsBefore = app.db().parents();
    Families familyBefore = app.db().families();

    app.lkParent().goHref();
    app.lkParent().selfRegistration(student);

    Thread.sleep(3000);//необходимо, т.к. не успевает сформироваться токен в бд

    ParentData parent = app.db().getTokenParent("Лид", "Лидов", "parent");
    String token = parent.getServices().getPassword().getReset().getToken();
    app.lkParent().activation(token);

    Leads leadsAfter = app.db().leads();
    Students studentsAfter = app.dbstudents().students();
    Parents parentsAfter = app.db().parents();
    Families familyAfter = app.db().families();

    studentClean = app.student().getNewStudentDB(studentsBefore, studentsAfter);
    parentClean = app.parent().getNewParentDB(parentsBefore, parentsAfter);
    familyClean = app.family().getNewFamilyDB(familyBefore, familyAfter);

    // LeadData leadAdd = lead.withId(leadClean.getId());
    assertThat(leadsAfter.size(), equalTo(leadsBefore.size()));
    assertThat(studentsAfter.size(), equalTo(studentsBefore.size() + 1));
    assertThat(parentsAfter.size(), equalTo(parentsBefore.size() + 1));
    assertThat(familyAfter.size(), equalTo(familyBefore.size() + 1));
    //  assertThat(after, equalTo(before.withAdded(leadAdd)));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    leadService.findByIdAndDelete(leadService.findById("selfRegistration"));
    taskService.drop();
    scheduleService.findByIdAndDelete(studentClean.getId());
    parentService.findByIdAndDelete(parentClean.getId());
    familyService.findByIdAndDelete(familyClean.getId());
  }
}
