package io.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.ParentService;
import io.itgen.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.itgen.model.FamilyData;
import io.itgen.model.ParentData;
import io.itgen.model.Parents;
import io.itgen.model.StudentData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentCreationTests extends TestBase {
  String id;

  @DataProvider
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/parents_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents = gson.fromJson(json, new TypeToken<List<ParentData>>() {
      }.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("parentCreate").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("forParentCreation").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("parentCreate").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date())
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);

  }

  @Test(dataProvider = "validParentsFromJson")
  public void testParentCreation(ParentData parent) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("forParentCreation");
    app.parent().create(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() + 1));

    id = app.parent().getIdNewParentDB(before, after);
    ParentData parentAdd = parent.withId(id).withFirstName(parent.getFirstName()).withLastName(parent.getLastName())
            .withCountry(parent.getCountry()).withCity(parent.getCity()).withTimeZone(parent.getTimeZone())
            .withLocate(parent.getLocate()).withNote(parent.getNote());
    assertThat(after, equalTo(before.withAdded(parentAdd)));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("forParentCreation");
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("parentCreate");
    ParentService parentService = new ParentService();
    parentService.findByIdAndDelete(id);
  }
}